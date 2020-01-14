package com.zh.poi.excel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * xlsx格式excel解析
 * 
 * poi的xssf+sax流式解析
 * 
 * @author 网盛数新
 *
 */
public class XlsxHandler implements SheetContentsHandler {
    
    private String data;
    private StylesTable stylesTable;

    private String fileSheetName;
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private String metastructSheetName;
    private int metastructTotalRowCount;

    // 0 based
    private int firstRow = -1;
    private int lastRow = -1;
    private int firstColumn = -1;
    private int lastColumn = -1;
    
    private boolean existSheet;
    
    private List<Map<String, Object>> sheetInfos = new ArrayList<Map<String, Object>>();
    
    private int prevRowIndex = 0;
    private File dataTemp;
    private BufferedWriter fileWriter;
    private BufferedReader fileReader;
    private StringBuilder rowData;

    public XlsxHandler(String path, String sheetName, int startRow,int startCol,int endRow,int endCol,String mSheetName,int mRowCount) throws IOException {
        this.data = path;
        this.fileSheetName = sheetName;
        this.startRow = startRow;
        this.startColumn = startCol;
        this.endRow = endRow;
        this.endColumn = endCol;
        this.metastructSheetName = mSheetName;
        this.metastructTotalRowCount = mRowCount;
        dataTemp = File.createTempFile("excel", null);
        fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataTemp), "utf-8"));
        rowData = new StringBuilder();
    }
    
    public XlsxHandler(String path) {
        this.data = path;
    }
    
    public void parse() throws Exception {
        OPCPackage pkg = OPCPackage.open(data);
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
        XSSFReader r = new XSSFReader(pkg);
        this.stylesTable = r.getStylesTable();
        XMLReader parser = SAXHelper.newXMLReader();
        parser.setContentHandler(new XSSFSheetXMLHandler(stylesTable, null, strings, this, new DataFormatter(), false));
        SheetIterator sheets = (SheetIterator) r.getSheetsData();
        while(sheets.hasNext()) {
            InputStream sheet = sheets.next();
            String currentSheetName = sheets.getSheetName();
            if (StringUtils.isNotBlank(fileSheetName) && !fileSheetName.equals(currentSheetName)) {
                continue;
            }
            existSheet = true;
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
            
            // 当前sheet信息
            Map<String, Object> currentSheetInfo = new HashMap<String, Object>();
            currentSheetInfo.put("sheetName", currentSheetName);
            currentSheetInfo.put("firstRowNum", this.firstRow + 1);
            currentSheetInfo.put("lastRowNum", this.lastRow + 1);
            currentSheetInfo.put("firstColNum", this.firstColumn + 1);
            currentSheetInfo.put("lastColNum", this.lastColumn + 1);
            sheetInfos.add(currentSheetInfo);
            clear();
        }
    }
    
    private void clear() {
        this.firstRow = -1;
        this.lastRow = -1;
        this.firstColumn = -1;
        this.lastColumn = -1;
    }

    @Override
    public void startRow(int rowNum) {
        if (StringUtils.isNotBlank(this.fileSheetName)) {
            rowData.setLength(0);
        }
    }

    @Override
    public void endRow(int rowNum) {
        if (StringUtils.isNotBlank(this.fileSheetName)) {
            if (rowNum - prevRowIndex > 1) {
                for (int i = 0 ; i < rowNum - prevRowIndex - 1 ; i++) {
                    try {
                        fileWriter.write("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (rowData.length() > 0 && rowData.charAt(rowData.length() - 1) == ',') {
                rowData.deleteCharAt(rowData.length() - 1);
            }
            rowData.append("\n");
            try {
                fileWriter.write(rowData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            prevRowIndex = rowNum;
        }
    }

    @Override
    public void cell(String cellReference, String formattedValue,
            XSSFComment comment) {
        if (StringUtils.isNotBlank(formattedValue)) {
            rowData.append(formattedValue);
        }
        rowData.append(",");
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        
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
    
    public String getFileSheetName() {
        return fileSheetName;
    }

    public void setFileSheetName(String fileSheetName) {
        this.fileSheetName = fileSheetName;
    }
    
    public boolean existSheet() {
        return this.existSheet;
    }

    public Map<String, Object> getSheetInfos() {
        Map<String, Object> sheetInfo = new HashMap<String, Object>();
        sheetInfo.put("sheets", sheetInfos);
        return sheetInfo;
    }

    public static void main(String[] args) throws Exception {
        XlsxHandler xlsxHandler = new XlsxHandler("d:/xlsx1.xlsx", "Sheet2", 0, 0, 1, 10, "Sheet1", 1);
        xlsxHandler.parse();
    }
}
