package com.zh.poi.excel;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;

public class XlsHandler implements HSSFListener {
    private POIFSFileSystem fs;

    /** Should we output the formula, or the value it has? */
    private boolean outputFormulaValues = true;

    /** For parsing Formulas */
    private EventWorkbookBuilder.SheetRecordCollectingListener workbookBuildingListener;
    private HSSFWorkbook stubWorkbook;

    private SSTRecord sstRecord;
    private FormatTrackingHSSFListener formatListener;

    private BoundSheetRecord[] orderedBSRs;
    private List<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

    // For handling formulas with string results
    private int nextRow;
    private int nextColumn;
    private boolean outputNextStringRecord;

    private final List<ExtendedFormatRecord> _xfRecords = new ArrayList<ExtendedFormatRecord>();
    private final Map<Integer, FormatRecord> _customFormatRecords = new HashMap<Integer, FormatRecord>();
    
    private boolean skipSheet = false;
    private boolean existSheet = false;
    private String fileSheetName;
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private String metastructSheetName;
    private int metastructTotalRowCount;

    private int firstRow = -1;
    private int lastRow = -1;
    private int firstColumn = -1;
    private int lastColumn = -1;
    private String currentSheetName;
    
    List<String> rowData = new ArrayList<String>();
    // 最终解析出的表格数据
    private List<List<String>> table;
    
    private List<Map<String, Object>> sheetInfos = new ArrayList<Map<String, Object>>();
    
    private int sheetIndex = 0;
    private List<String> sheetNames = new ArrayList<String>();
    
    /**
     * Creates a new XLS -> CSV converter
     * @throws IOException
     * @throws FileNotFoundException
     */
    public XlsHandler(String path, String sheetName, int startRow,int startCol,int endRow,int endCol,String mSheetName,int mRowCount) throws IOException, FileNotFoundException {
        this.fs = new POIFSFileSystem(new FileInputStream(path));
        this.fileSheetName = sheetName;
        this.startRow = startRow;
        this.startColumn = startCol;
        this.endRow = endRow;
        this.endColumn = endCol;
        this.metastructSheetName = mSheetName;
        this.metastructTotalRowCount = mRowCount;
        table = new ArrayList<List<String>>(10000);
    }
    
    public XlsHandler(byte[] data) throws IOException {
        this.fs = new POIFSFileSystem(new ByteArrayInputStream(data));
    }

    public void parse() throws IOException {
        MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
        formatListener = new FormatTrackingHSSFListener(listener);
        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();
        if(outputFormulaValues) {
            request.addListenerForAllRecords(formatListener);
        } else {
            workbookBuildingListener = new EventWorkbookBuilder.SheetRecordCollectingListener(formatListener);
            request.addListenerForAllRecords(workbookBuildingListener);
        }
        factory.processWorkbookEvents(request, fs);
    }

    /**
     * Main HSSFListener method, processes events, and outputs the
     *  CSV as the file is processed.
     */
    @Override
    public void processRecord(Record record) {
        int thisRow = -1;
        int thisColumn = -1;   // 当前cell所在列
        String thisStr = null;  // 单元格内容

        processRecordInternally(record);

        switch (record.getSid()) {
            case BoundSheetRecord.sid:
                BoundSheetRecord boundSheetRecord = (BoundSheetRecord)record;
                boundSheetRecords.add(boundSheetRecord);
                currentSheetName = (boundSheetRecord.getSheetname());
                sheetNames.add(currentSheetName);
                break;
            case BOFRecord.sid:
                BOFRecord br = (BOFRecord)record;
                if(br.getType() == BOFRecord.TYPE_WORKSHEET) {
                    String currentSheetName = sheetNames.get(sheetIndex);
                    if (StringUtils.isNotBlank(fileSheetName) && !currentSheetName.equals(fileSheetName)) {
                        this.skipSheet = true;
                    } else {
                        this.skipSheet = false;
                        this.existSheet = true;
                    }
                    if (this.skipSheet) {
                        return;
                    }
                    if(workbookBuildingListener != null && stubWorkbook == null) {
                        stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
                    }
                    if(orderedBSRs == null) {
                        orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
                    }
                    Map<String, Object> sheetInfo = new HashMap<String, Object>();
                    sheetInfo.put("sheetName", sheetNames.get(sheetIndex));
                    sheetInfos.add(sheetInfo);
                }
                break;
            case DimensionsRecord.sid:
                if (this.skipSheet) {
                    sheetIndex++;
                    return;
                }
                DimensionsRecord dimensionsRecord = (DimensionsRecord) record;
                this.firstRow = dimensionsRecord.getFirstRow();
                this.lastRow = dimensionsRecord.getLastRow() - 1;
                this.firstColumn = dimensionsRecord.getFirstCol();
                this.lastColumn = dimensionsRecord.getLastCol() - 1;
                
                Map<String, Object> sheetInfo = sheetInfos.get(sheetIndex);
                sheetInfo.put("firstRowNum", this.firstRow + 1);
                sheetInfo.put("lastRowNum", this.lastRow + 1);
                sheetInfo.put("firstColNum", this.firstColumn + 1);
                sheetInfo.put("lastColNum", this.lastColumn + 1);
                sheetIndex++;
                break;
            case SSTRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                sstRecord = (SSTRecord) record;
                break;
            case BlankRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                BlankRecord brec = (BlankRecord) record;

                thisRow = brec.getRow();
                thisColumn = brec.getColumn();
                thisStr = "";
                break;
            case BoolErrRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                BoolErrRecord berec = (BoolErrRecord) record;

                thisRow = berec.getRow();
                thisColumn = berec.getColumn();
                thisStr = "";
                break;
            case FormulaRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                FormulaRecord frec = (FormulaRecord) record;

                thisRow = frec.getRow();
                thisColumn = frec.getColumn();

                if(outputFormulaValues) {
                    if(Double.isNaN( frec.getValue() )) {
                        outputNextStringRecord = true;
                        nextRow = frec.getRow();
                        nextColumn = frec.getColumn();
                    } else {
                        thisStr = formatListener.formatNumberDateCell(frec);
                    }
                } else {
                    thisStr = HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression());
                }
                break;
            case StringRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                if(outputNextStringRecord) {
                    StringRecord srec = (StringRecord)record;
                    thisStr = srec.getString();
                    thisRow = nextRow;
                    thisColumn = nextColumn;
                    outputNextStringRecord = false;
                }
                break;
            case LabelRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                LabelRecord lrec = (LabelRecord) record;

                thisRow = lrec.getRow();
                thisColumn = lrec.getColumn();
                thisStr = lrec.getValue();
                break;
            case LabelSSTRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                LabelSSTRecord lsrec = (LabelSSTRecord) record;

                thisRow = lsrec.getRow();
                thisColumn = lsrec.getColumn();
                if(sstRecord == null) {
                    thisStr ="(No SST Record, can't identify string)";
                } else {
                    thisStr = sstRecord.getString(lsrec.getSSTIndex()).toString();
                }
                break;
            case NumberRecord.sid:
                if (this.skipSheet) {
                    return;
                }
                NumberRecord numrec = (NumberRecord) record;
                thisRow = numrec.getRow();
                thisColumn = numrec.getColumn();
                thisStr = formatNumberDateCell(numrec);
                break;
            default:
                break;
        }

        if (this.skipSheet) {
            return;
        }
        
        // Handle missing column
        if(record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord mc = (MissingCellDummyRecord)record;
            thisRow = mc.getRow();
            thisColumn = mc.getColumn();
            thisStr = "";
        }

        // 当前record内容
        if(StringUtils.isNotBlank(thisStr) && StringUtils.isNotBlank(fileSheetName)) {
            if (thisColumn >= rowData.size()) {
                for (int i = rowData.size() ; i < thisColumn + 1 ; i++) {
                    rowData.add(null);
                }
            }
            rowData.set(thisColumn, thisStr);
        }

        // 行尾
        if(record instanceof LastCellOfRowDummyRecord && StringUtils.isNotBlank(fileSheetName)) {
            List<String> newRowData = new ArrayList<String>(rowData.size());
//            CollectionUtils.addAll(newRowData, rowData.iterator());
            for (int i = 0 ; i < rowData.size() ; i++) {
                newRowData.add(rowData.get(i));
            }
            table.add(newRowData);
            rowData = new ArrayList<String>();
        }
    }

    private int getFormatIndex(CellValueRecordInterface cell) {
        ExtendedFormatRecord xfr = _xfRecords.get(cell.getXFIndex());
        if (xfr == null) {
            return -1;
        }
        return xfr.getFormatIndex();
    }

    private String formatNumberDateCell(CellValueRecordInterface cell) {
        double value;
        int formatIndex = getFormatIndex(cell);
        String formatString = getFormatString(cell);
        DateUtil.isADateFormat(formatIndex,formatString);
        if (cell instanceof NumberRecord) {
            value = ((NumberRecord) cell).getValue();
            if (!DateUtil.isADateFormat(formatIndex, formatString)) {
                return String.valueOf(value);
            }
        } else if (cell instanceof FormulaRecord) {
            value = ((FormulaRecord) cell).getValue();
        } else {
            throw new IllegalArgumentException("Unsupported CellValue Record passed in " + cell);
        }
        DataFormatter _formatter = new DataFormatter();
        return _formatter.formatRawCellContents(value, formatIndex, formatString);
    }

    private String getFormatString(CellValueRecordInterface cell) {
        int formatIndex = getFormatIndex(cell);
        if (formatIndex == -1) {
            // Not found
            return null;
        }
        return getFormatString(formatIndex);
    }

    private String getFormatString(int formatIndex) {
        String format = null;
        if (formatIndex >= HSSFDataFormat.getNumberOfBuiltinBuiltinFormats()) {
            FormatRecord tfr = _customFormatRecords.get(formatIndex);
            if (tfr == null) {
            } else {
                format = tfr.getFormatString();
            }
        } else if(formatIndex == 14) {
            format = "yyyy/MM/dd";
        } else {
            // "yyyy/MM/dd"
            format = HSSFDataFormat.getBuiltinFormat((short) formatIndex);
        }
        return format;
    }

    private void processRecordInternally(Record record) {
        if (record instanceof FormatRecord) {
            FormatRecord fr = (FormatRecord) record;
            _customFormatRecords.put(Integer.valueOf(fr.getIndexCode()), fr);
        }
        if (record instanceof ExtendedFormatRecord) {
            ExtendedFormatRecord xr = (ExtendedFormatRecord) record;
            _xfRecords.add(xr);
        }
    }

    public boolean existSheet() {
        return this.existSheet;
    }
    
    
    public int firstRow() {
        return firstRow;
    }
    
    public int lastRow() {
        return lastRow;
    }
    
    public int firstColumn() {
        return firstColumn;
    }
    
    public int lastColumn() {
        return lastColumn;
    }

    public Map<String, Object> getSheetInfos() {
        Map<String, Object> sheetInfo = new HashMap<String, Object>();
        sheetInfo.put("sheets", sheetInfos);
        return sheetInfo;
    }

    public static void main(String[] args) throws Exception {
        XlsHandler xlsHandler= new XlsHandler("d:/xls.xls", "Sheet2", 0, 0, 1, 10, "Sheet1", 1);
        xlsHandler.parse();
        System.out.printf("1");
    }
}
