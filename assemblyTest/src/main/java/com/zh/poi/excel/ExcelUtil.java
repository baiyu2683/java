package com.zh.poi.excel;

import java.io.*;
import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class ExcelUtil {

    private static Workbook workbook = null;
    
    public static void main(String[] args) throws Exception {
        excelImport();
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
        Sheet sheet = workbook.createSheet("hello");
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
            if (CollectionUtils.isNotEmpty(nodes)) {
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
    public static void excelImport() throws Exception {
        processAllSheets("d:/xlsx.xlsx");
    }


    public static void processAllSheets(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = SAXHelper.newXMLReader();
        ContentHandler handler = new MyHandler(sst);
        parser.setContentHandler(handler);

        Iterator<InputStream> sheets = r.getSheetsData();
        while(sheets.hasNext()) {
            System.out.println("Processing new sheet:\n");
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
            System.out.println("");
        }
    }

    static class MyHandler extends DefaultHandler {

        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;

        public MyHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        @Override
        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {
            // c => cell
            System.out.println(name);
            if(name.equals("c")) {
                // Print the cell reference
                System.out.println(attributes.getValue("r") + "-");
                // Figure out if the value is an index in the SST
                String cellType = attributes.getValue("t");
                if(cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
            }
            // Clear contents cache
            lastContents = "";
        }

        @Override
        public void endElement(String uri, String localName, String name)
                throws SAXException {
            // Process the last contents as required.
            // Do now, as characters() may be called more than once
            if(nextIsString) {
                int idx = Integer.parseInt(lastContents);
                List<CTRElt> ctrElts = sst.getEntryAt(idx).getRList();
                for (CTRElt ctrElt : ctrElts) {
                    lastContents += ctrElt.getT();
                }
                nextIsString = false;
            }
            // v => contents of a cell
            // Output after we've seen the string contents
            if(name.equals("v")) {
                System.out.println(lastContents);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            lastContents += new String(ch, start, length);
        }

    }
}
