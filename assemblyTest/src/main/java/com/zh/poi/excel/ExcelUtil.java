package com.zh.poi.excel;

import java.io.*;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.xml.sax.*;

public class ExcelUtil {

    private static Workbook workbook = null;
    
    public static void main(String[] args) throws Exception {
        excelXlsxImport();
    }

    /**
     * xls
     * @throws IOException
     */
    public static void excelXls() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        HSSFRow row = sheet.createRow(0);

        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        HSSFCell cell1 = row.createCell(1);

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cell1.setCellStyle(cellStyle);

        HSSFRichTextString richTextString = new HSSFRichTextString("一二三、\r\n四五六、\r\n七八九");
        Font font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short)20);
        font1.setItalic(true);
        richTextString.applyFont(0, 3, font1);

        Font font2 = workbook.createFont();
        font2.setFontName("隶书");
        font2.setFontHeightInPoints((short)20);
        font2.setItalic(true);
        font2.setBold(true);
        font2.setStrikeout(true);
        font2.setUnderline(Font.U_SINGLE);
        font2.setColor(Font.COLOR_RED);
        font2.setTypeOffset(Font.SS_SUPER);
        richTextString.applyFont(6, 9, font2);

        cell1.setCellValue(richTextString);

        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/xls.xls"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }

    /**
     * xls
     * @throws IOException
     */
    public static void excelXlsx() throws IOException {
        String html = "<p>\n" +
                "    <em><span style=\"text-decoration:underline;\"><strong><span style=\"font-size:24px\">阿瑟发</span></strong></span></em>\n" +
                "</p>";

        workbook = new XSSFWorkbook();
        //新建工作表
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("hello");
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        Row row = sheet.createRow(0);

        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        Cell cell1 = row.createCell(1);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cell1.setCellStyle(cellStyle);
        RichTextString richTextString = newRichTextString("1.中文\r\n2.中文2");

        Font font1 = workbook.createFont();
        font1.setBold(true);
        font1.setItalic(true);
        font1.setUnderline(Font.U_SINGLE);
        font1.setStrikeout(true);
        font1.setColor(Font.COLOR_RED);
        font1.setFontHeightInPoints(Short.valueOf("20"));
        font1.setFontName("隶书");
        richTextString.applyFont(2, 4, font1);

        cell1.setCellValue(richTextString);

        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream(new File("D:/xlsx.xlsx"));
        workbook.write(fos);
        workbook.close();
        fos.close();
        System.out.println("finished...");
    }

    private static RichTextString newRichTextString(String x) {
        return new XSSFRichTextString(x);
    }

    //TODO 富文本设置
    private static RichTextString richText(Cell poiCell, String text) {
        // 存每一段文本的字体设置, key: startIndex-endIndex  value: Font
        Map<String, Font> fontMap = new LinkedHashMap<String, Font>();
        // 样式
        Map<String, String> styleMap = new HashMap<String, String>();
        // 存所有文本
        StringBuilder content = new StringBuilder();

        Document document = Jsoup.parse(text);
        Elements body = document.getElementsByTag("body");
        ListIterator<Element> eleIterator = body.listIterator();
        while(eleIterator.hasNext()) {
            handleRichText(eleIterator.next(), content, styleMap, fontMap);
        }
        RichTextString richText = newRichTextString(content.toString());
        // TODO 根据索引设置每段的字体样式
        for (Map.Entry<String, org.apache.poi.ss.usermodel.Font> entry : fontMap.entrySet()) {
            String key = entry.getKey();
            org.apache.poi.ss.usermodel.Font font = entry.getValue();
            String[] indexs = key.split("-");
            richText.applyFont(Integer.parseInt(indexs[0]), Integer.parseInt(indexs[1]), font);
        }
        return richText;
    }

    private static void handleRichText(Node node, StringBuilder content, Map<String, String> styleMap, Map<String, org.apache.poi.ss.usermodel.Font> fontMap) {
        if (node instanceof Element) {
            Element ele = (Element) node;
            String tagName = ele.tagName();
            if ("p".equalsIgnoreCase(tagName)
                    || "div".equalsIgnoreCase(tagName)
                    || "ol".equalsIgnoreCase(tagName)
                    || "ul".equalsIgnoreCase(tagName)
                    || "li".equalsIgnoreCase(tagName)
                    || "span".equalsIgnoreCase(tagName)
                    || "a".equalsIgnoreCase(tagName)) {
                styleAttr(ele.attr("style"), styleMap);
            } else if ("strong".equalsIgnoreCase(tagName)) {
                styleMap.put("bold", "1");
            } else if ("em".equalsIgnoreCase(tagName)) {
                styleMap.put("italic", "1");
            } else if ("br".equalsIgnoreCase(tagName)) {
                content.append("\r\n");
            } else if ("sub".equalsIgnoreCase(tagName)) {
                styleMap.put("ss_super", "1");
            } else if ("sup".equalsIgnoreCase(tagName)) {
                styleMap.put("ss_sub", "1");
            }
            List<Node> nodes = ele.childNodes();
            if (!CollectionUtils.isEmpty(nodes)) {
                Map<String, String> newStyleMap = new HashMap<String, String>(styleMap);
                for (Node n : nodes) {
                    handleRichText(n, content, newStyleMap, fontMap);
                }
            }
        } else {
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                String text = textNode.text();
                int startIndex = content.length();
                int endIndex = startIndex + text.length();
                content.append(text);
                org.apache.poi.ss.usermodel.Font poiFont = workbook.createFont();
                poiFont.setFontName("宋体");
                poiFont.setFontHeightInPoints((short)9);
                if (styleMap.containsKey("font-size")) {
                    String fontSize = styleMap.get("font-size");
                    poiFont.setFontHeightInPoints(Short.valueOf(fontSize));
                }
                if (styleMap.containsKey("font-family")) {
                    poiFont.setFontName(styleMap.get("font-family"));
                }
                if (styleMap.containsKey("text-decoration")) {
                    String textDecorationString = styleMap.get("text-decoration");
                    String[] textDecorations = textDecorationString.split(";");
                    for (String textDecoration : textDecorations) {
                        if ("underline".equalsIgnoreCase(textDecoration)) {
                            poiFont.setUnderline(org.apache.poi.ss.usermodel.Font.U_SINGLE);
                        } else if ("line-through".equalsIgnoreCase(textDecoration)) {
                            poiFont.setStrikeout(true);
                        }
                    }
                }
                if (styleMap.containsKey("bold")) {
                    poiFont.setBold(true);
                }
                if (styleMap.containsKey("italic")) {
                    poiFont.setItalic(true);
                }
                // TODO 颜色
                if (styleMap.containsKey("color")) {
                    poiFont.setColor(Short.valueOf(styleMap.get("color").replaceFirst("#", ""), 16));
                }
                if (styleMap.containsKey("ss_super")) {
                    poiFont.setTypeOffset(org.apache.poi.ss.usermodel.Font.SS_SUPER);
                } else if (styleMap.containsKey("ss_sub")) {
                    poiFont.setTypeOffset(org.apache.poi.ss.usermodel.Font.SS_SUB);
                }
                fontMap.put(startIndex + "-" + endIndex, poiFont);
            }
        }
    }

    private static void styleAttr(String styleString, Map<String, String> styleMap) {
        if (StringUtils.isBlank(styleString)) {
            return;
        }
        String[] styles = styleString.split(";");
        for (String style : styles) {
            String[] styleContent = style.split(":");
            if (styleContent.length == 2) {
                String styleName = styleContent[0];
                String styleValue = styleContent[1];
                if ("text-decoration".equalsIgnoreCase(styleName)) {
                    if (styleMap.containsKey("text-decoration")) {
                        String oldValue = styleMap.get("text-decoration");
                        styleMap.put("text-decoration", oldValue + ";" + styleValue);
                    } else {
                        styleMap.put("text-decoration", styleValue);
                    }
                } else {
                    styleMap.put(styleName, styleValue);
                }
            }
        }
    }

    /**
     * excel导入
     */
    public static void excelXlsxImport() throws Exception {
//        Workbook workbook = WorkbookFactory.create(new File("d:/一千行.xlsx"));
//        System.out.println(1);
        processAllSheets("d:/xlsx.xlsx");
//        processOneSheets("d:/一千行.xls", "Sheet1");
    }

    public static void excelXlsImport() throws IOException {
        List<String> nullRowList = new ArrayList<>();
        nullRowList.add("需求标题");
        nullRowList.add("任务标题");
        Xls2CSV xls2csv = new Xls2CSV(
                "d:/xls.xls",
                "电销系统", nullRowList);
        List<String[]> list = xls2csv.readerExcel();

        List<Map<String, String>> resultList = new LinkedList<Map<String, String>>();
        for (int i=0; i<list.size(); i++) {
            Map<String, String> valueMap = new HashMap<String, String>();
            for (int j=0; j<list.get(i).length; j++) {
                if(list.get(0)[j] != null && !"".equals(list.get(0)[j])) {
                    valueMap.put(list.get(0)[j], list.get(i)[j]);
                }
            }
            // 只获取数据，不要标题
            if(i > 0) {
                resultList.add(valueMap);
            }
        }
        System.out.println("\nresultList.size()：" + resultList.size());
        System.out.println("\nresultList：" + resultList);
    }


    public static void processAllSheets(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
        XSSFReader r = new XSSFReader(pkg);
        StylesTable stylesTable = r.getStylesTable();

        XMLReader parser = SAXHelper.newXMLReader();
        ContentHandler handler = new XSSFSheetXMLHandler(stylesTable, null, strings, new XlsxHandler(), new DataFormatter(), false);
        parser.setContentHandler(handler);
        XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator)r.getSheetsData();
        while(sheets.hasNext()) {
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
    }

    private static class XlsxHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
        @Override
        public void startRow(int rowNum) {
            System.out.println("开始: " + rowNum);
        }

        @Override
        public void endRow(int rowNum) {
            System.out.println("结束: " + rowNum);
        }

        @Override
        public void cell(String cellReference, String formattedValue, XSSFComment comment) {
            System.out.println(cellReference + "-" + formattedValue);
        }

        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {

        }
    }

    private static class Xls2CSV implements HSSFListener {
        private int minColumns;
        private POIFSFileSystem fs;
        private PrintStream output;

        private int lastRowNumber;
        private int lastColumnNumber;

        /** Should we output the formula, or the value it has? */
        private boolean outputFormulaValues = true;

        /** For parsing Formulas */
        private EventWorkbookBuilder.SheetRecordCollectingListener workbookBuildingListener;
        private HSSFWorkbook stubWorkbook;

        // Records we pick up as we process
        private SSTRecord sstRecord;
        private FormatTrackingHSSFListener formatListener;

        /** So we known which sheet we're on */
        private int sheetIndex = -1;
        private BoundSheetRecord[] orderedBSRs;
        private List<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();

        // For handling formulas with string results
        private int nextRow;
        private int nextColumn;
        private boolean outputNextStringRecord;

        String[] recordArray = new String[400];  ;   // 每行为一个数组
        List<String[]> rowsList = new ArrayList<String[]>();  //当前sheet页的所有行

//        private int requireHeadColumn = 99;  // 需求标题所在列
//        private int exTaskHead = 99;  //任务标题所在列
        private List<String> nullRowList;   // 包含"需求标题", "任务标题"

//        private String sheetName = "";
        private final List<ExtendedFormatRecord> _xfRecords = new ArrayList<ExtendedFormatRecord>();
        private final Map<Integer, FormatRecord> _customFormatRecords = new HashMap<Integer, FormatRecord>();

        /**
         * Creates a new XLS -> CSV converter
         * @param fs The POIFSFileSystem to process
         * @param output The PrintStream to output the CSV to
         */
        public Xls2CSV(POIFSFileSystem fs, PrintStream output, List<String> nullRowList, String sheetName) {
                this.fs = fs;
                this.output = output;
                this.minColumns = 100;
                this.nullRowList = nullRowList;
//                this.sheetName = sheetName;
        }

        /**
         * Creates a new XLS -> CSV converter
         * @param filename The file to process
         * @throws IOException
         * @throws FileNotFoundException
         */
        public Xls2CSV(String filename, String sheetName, List<String> nullRowList) throws IOException, FileNotFoundException {
            this(new POIFSFileSystem(new FileInputStream(filename)), System.out, nullRowList, sheetName);
        }

        /**
         * Initiates the processing of the XLS file to CSV
         */
        public List<String[]> readerExcel() throws IOException {
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
            return rowsList;
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
                    boundSheetRecords.add((BoundSheetRecord)record);
                    break;
                case BOFRecord.sid:
                    BOFRecord br = (BOFRecord)record;
                    if(br.getType() == BOFRecord.TYPE_WORKSHEET) {
                        if(workbookBuildingListener != null && stubWorkbook == null) {
                            stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
                        }
                        sheetIndex++;
                        if(orderedBSRs == null) {
                            orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
                        }
                    }
                    break;

                case SSTRecord.sid:
                    sstRecord = (SSTRecord) record;
                    break;
                case BlankRecord.sid:
                    BlankRecord brec = (BlankRecord) record;

                    thisRow = brec.getRow();
                    thisColumn = brec.getColumn();
                    thisStr = "";
                    break;
                case BoolErrRecord.sid:
                    BoolErrRecord berec = (BoolErrRecord) record;

                    thisRow = berec.getRow();
                    thisColumn = berec.getColumn();
                    thisStr = "";
                    break;
                case FormulaRecord.sid:
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
                    if(outputNextStringRecord) {
                        StringRecord srec = (StringRecord)record;
                        thisStr = srec.getString();
                        thisRow = nextRow;
                        thisColumn = nextColumn;
                        outputNextStringRecord = false;
                    }
                    break;
                case LabelRecord.sid:
                    LabelRecord lrec = (LabelRecord) record;

                    thisRow = lrec.getRow();
                    thisColumn = lrec.getColumn();
                    thisStr = lrec.getValue();
                    break;
                case LabelSSTRecord.sid:
                    LabelSSTRecord lsrec = (LabelSSTRecord) record;

                    thisRow = lsrec.getRow();
                    thisColumn = lsrec.getColumn();
                    if(sstRecord == null) {
                        thisStr ="(No SST Record, can't identify string)";
                    } else {
                        thisStr = sstRecord.getString(lsrec.getSSTIndex()).toString();
                    }
                    break;
                case NoteRecord.sid:
                    NoteRecord nrec = (NoteRecord) record;

                    thisRow = nrec.getRow();
                    thisColumn = nrec.getColumn();
                    thisStr = "(TODO)";
                    break;
                case NumberRecord.sid:
                    NumberRecord numrec = (NumberRecord) record;
                    thisRow = numrec.getRow();
                    thisColumn = numrec.getColumn();
                    // Format
                    thisStr = formatNumberDateCell(numrec);
                    break;
                case RKRecord.sid:
                    RKRecord rkrec = (RKRecord) record;

                    thisRow = rkrec.getRow();
                    thisColumn = rkrec.getColumn();
                    thisStr = "(TODO)";
                    break;
                default:
                    break;
            }

            // Handle new row
            if(thisRow != -1 && thisRow != lastRowNumber) {
                lastColumnNumber = -1;
            }

            // Handle missing column
            if(record instanceof MissingCellDummyRecord) {
                MissingCellDummyRecord mc = (MissingCellDummyRecord)record;
                thisRow = mc.getRow();
                thisColumn = mc.getColumn();
                thisStr = "";
            }

            // If we got something to print out, do so
            if(thisStr != null) {
                recordArray[thisColumn] = thisStr;
            }

            // Update column and row count
            if(thisRow > -1) {
                lastRowNumber = thisRow;
            }
            if(thisColumn > -1) {
                lastColumnNumber = thisColumn;
            }

            // Handle end of row
            if(record instanceof LastCellOfRowDummyRecord) {
                // Print out any missing commas if needed
                if(minColumns > 0) {
                    // Columns are 0 based
                    if(lastColumnNumber == -1) { lastColumnNumber = 0; }
                    for(int i=lastColumnNumber; i<(minColumns); i++) {
                    }
                    rowsList.add(recordArray.clone());

                }
                lastColumnNumber = -1;
            }
        }

        public int getFormatIndex(CellValueRecordInterface cell) {
            ExtendedFormatRecord xfr = _xfRecords.get(cell.getXFIndex());
            if (xfr == null) {
                return -1;
            }
//            _xfRecords.remove(cell.getXFIndex());
            return xfr.getFormatIndex();
        }

        public String formatNumberDateCell(CellValueRecordInterface cell) {
            double value;
            if (cell instanceof NumberRecord) {
                value = ((NumberRecord) cell).getValue();
            } else if (cell instanceof FormulaRecord) {
                value = ((FormulaRecord) cell).getValue();
            } else {
                throw new IllegalArgumentException("Unsupported CellValue Record passed in " + cell);
            }
            // Format, using the nice new
            // HSSFDataFormatter to do the work for us"yyyy/MM/dd"
            DataFormatter _formatter = new DataFormatter();
            return _formatter.formatRawCellContents(value, getFormatIndex(cell), getFormatString(cell));
        }

        public String getFormatString(CellValueRecordInterface cell) {
            int formatIndex = getFormatIndex(cell);
            if (formatIndex == -1) {
                // Not found
                return null;
            }
            return getFormatString(formatIndex);
        }

        public String getFormatString(int formatIndex) {
            String format = null;
            if (formatIndex >= HSSFDataFormat.getNumberOfBuiltinBuiltinFormats()) {
                FormatRecord tfr = _customFormatRecords.get(formatIndex);
                if (tfr == null) {
                } else {
                    format = tfr.getFormatString();
                }
//                _customFormatRecords.remove(formatIndex);
            } else if(formatIndex == 14) {
                format = "yyyy/MM/dd";
            } else {
                // "yyyy/MM/dd"
                format = HSSFDataFormat.getBuiltinFormat((short) formatIndex);
            }
            return format;
        }

        public void processRecordInternally(Record record) {
            if (record instanceof FormatRecord) {
                FormatRecord fr = (FormatRecord) record;
                _customFormatRecords.put(Integer.valueOf(fr.getIndexCode()), fr);
            }
            if (record instanceof ExtendedFormatRecord) {
                ExtendedFormatRecord xr = (ExtendedFormatRecord) record;
                _xfRecords.add(xr);
            }
        }

    }
}
