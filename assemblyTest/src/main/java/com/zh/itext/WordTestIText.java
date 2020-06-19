package com.zh.itext;

//import com.lowagie.text.*;
//import com.lowagie.text.Font;
//import com.lowagie.text.html.simpleparser.HTMLWorker;
//import com.lowagie.text.html.simpleparser.StyleSheet;
//import com.lowagie.text.pdf.DefaultSplitCharacter;
//import com.lowagie.text.rtf.RtfWriter2;
//import com.lowagie.text.rtf.style.RtfFont;
//import com.lowagie.text.rtf.table.RtfBorderGroup;
//import com.lowagie.text.rtf.table.RtfCell;
//
//import java.awt.*;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.StringReader;
//import java.math.BigDecimal;
//import java.util.ArrayList;

/**
 *
 * 使用itext导出word
 * Author: Administrator <br/>
 * Date: 2018-07-10 <br/>
 */
public class WordTestIText {
//
//    public static void main(String[] args) throws IOException, DocumentException {
//
//        // 字体注册
//        FontFactory.registerDirectory("E:\\apache-tomcat-6.0.45\\webapps\\HappyServer\\WEB-INF\\classes\\font\\fontResource");
//        FontFactory.defaultEncoding = "utf8";
//
//        Document document = new Document();
//
//        RtfWriter2.getInstance(document, new FileOutputStream("d:/itext.doc"));
//        document.open();
//
//        Table table = new Table(1, 1);
//        table.setPadding(0);
//        table.setSpacing(0);
//        table.setAutoFillEmptyCells(true);
//
//        RtfCell cell = new RtfCell();
//
//        String html = "<p>\n" +
//                "    <strong>粗体</strong>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    <i>斜体</em>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    <span style=\"text-decoration:underline;\">下划线</span>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    <span style=\"text-decoration:line-through;\">删除线</span>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    x<sup>上标</sup>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    x<sub>下标</sub>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    <span style=\"color:#ffff00\">字体颜色</span>\n" +
//                "</p>\n" +
//                "<p>\n" +
//                "    <span style=\"background-color: rgb(0, 176, 80);\">字体背景色</span>\n" +
//                "</p>\n" +
//                "<ol>" +
//                "    <li>有序列表1</li><li>有序列表2</li>" +
//                "</ol>" +
//                "<div>" +
//                "    <ul>" +
//                "        <li>" +
//                "            无序列表1<br/>" +
//                "        </li>" +
//                "        <li>" +
//                "            无序列表2" +
//                "        </li>" +
//                "    </ul>" +
//                "    <h1>" +
//                "        h1" +
//                "    </h1>" +
//                "</div>" +
//                "<h2>\n" +
//                "    h2\n" +
//                "</h2>\n" +
//                "<p style=\"text-align: center;\">\n" +
//                "    <span style=\"font-family: 隶书, SimLi; font-size: 32px; background-color: rgb(0, 176, 240);color:#ff0000\">字体设置</span>\n" +
//                "</p>\n" +
//                "<p style=\"text-align: center;\">\n" +
//                "    <br/>\n" +
//                "</p>\n" +
//                "<p style=\"text-align: left;\">\n" +
//                "    <a href=\"http://www.baidu.com\" target=\"_blank\" title=\"百度首页\">http://www.baidu.com</a><br/>\n" +
//                "</p>\n" +
//                "<hr/>\n" +
//                "<p style=\"text-align: left;\">\n" +
//                "    分割线\n" +
//                "</p>";
////        html = "<p><span class=\"through\" style=\"text-decoration:line-through;\">删除线</span></p>";
//        StyleSheet styleSheet = new StyleSheet();
//        styleSheet.loadStyle("through", "text-decoration", "line-through");
//        ArrayList<Element> htmlList = HTMLWorker.parseToList(new StringReader(html), styleSheet);
//
//        for (Element element : htmlList) {
//            if (element instanceof Paragraph) {
//                Paragraph paragraph = (Paragraph) element;
//                ArrayList<Chunk> chunks = paragraph.getChunks();
//                for (Chunk chunk : chunks) {
//                    String fontName = chunk.getFont().getFamilyname();
//                    if ("unknown".equalsIgnoreCase(fontName)) {
//                        Font font = new Font();
//                        font.setFamily("宋体");
//                        font.setSize(18);
//                        font.setStyle(Font.BOLD);
//                        font.setColor(Color.BLUE);
//                        chunk.setFont(font);
//                    }
//                }
//            }
//            cell.addElement(element);
//            System.out.println("-----------------------");
//        }
//
//        Color paramColor = Color.BLUE;
//        RtfBorderGroup localRtfBorderGroup = new RtfBorderGroup();
//        localRtfBorderGroup.addBorder(1, 1, 1.0F, paramColor);
//        localRtfBorderGroup.addBorder(8, 1, 1.0F, paramColor);
//        localRtfBorderGroup.addBorder(2, 1, 1.0F, paramColor);
//        localRtfBorderGroup.addBorder(4, 1, 1.0F, paramColor);
//        cell.setBorders(localRtfBorderGroup);
//
////        cell.setBackgroundColor(Color.BLUE);
//
//        table.addCell(cell);
//        BigDecimal decimal = new BigDecimal(60 * 15);
//        table.getRow(0).setHeight(decimal.intValue());
//
//        document.add(table);
//
//        document.close();
//        System.out.println("finished...");
//    }
}
