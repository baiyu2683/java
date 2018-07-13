package com.zh.itext;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.table.RtfCell;
import com.lowagie.text.rtf.table.RtfRow;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class WordGenerator {

    static {
        FontFactory.register("D:\\fonts\\msyh.ttf");
        FontFactory.register("D:\\fonts\\msyhbd.ttf");
    }

    public static void main(String[] args) throws IOException, DocumentException {
        String html_content = "<p>\n" +
                "    &nbsp;表头1\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>\n" +
                "<p>\n" +
                "    <div style=\"color: rgb(255, 192, 0);\">表头2</div>\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>\n" +
                "<p>\n" +
                "    表头3\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>\n" +
                "<ol>\n" +
                "    <li>\n" +
                "        列表1\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        <div style=\"color: rgb(255, 0, 0);\">&nbsp;列表2</div>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        &nbsp;列表3\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        列表4\n" +
                "    </li>\n" +
                "</ol>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>";

        Document document = new Document(PageSize.A4);
        RtfWriter2.getInstance(document, new FileOutputStream("d:/itext.rtf"));
        document.open();

        Table table = new Table(1, 1);

        RtfCell cell = new RtfCell();
        Paragraph paragraph = new Paragraph();
        StyleSheet styleSheet = new StyleSheet();
        ArrayList htmlList = HTMLWorker.parseToList(new StringReader(html_content), styleSheet);
        ArrayList<Chunk> paramList = new ArrayList<>();
        for (int i = 0 ; i < htmlList.size() ; i++) {
            Element element = (Element) htmlList.get(i);
            int type = element.type();
            System.out.println("type: " + type);
            switch (type)
            {
                case Element.PARAGRAPH:
                    Paragraph localParagraph = (Paragraph)element;
                    localParagraph.setSpacingAfter(0);
                    localParagraph.setSpacingBefore(0);
                    paramList.addAll(localParagraph.getChunks());
                    break;
                case Element.CHUNK:
                    Chunk localChunk = (Chunk)element;
                    paramList.add(localChunk);
                    break;
                case Element.LIST:
                    List list = (List) element;
                    paramList.addAll(list.getChunks());
                    break;
                case Element.LISTITEM:
                    ListItem localListItem = (ListItem)element;
                    paramList.addAll(localListItem.getChunks());
                    break;
                case Element.PTABLE: {
                }
            }
        }
        for (Chunk chunk : paramList) {
            RtfFont rtfFont = new RtfFont("宋体", 16, Font.NORMAL, Color.BLACK);
            if (chunk.getFont() != null) {
                chunk.setFont(rtfFont);
            }
            paragraph.add(chunk);
            paragraph.setFont(rtfFont);
        }
        paragraph.setSpacingAfter(10);
        paragraph.setSpacingBefore(10);
        cell.add(paragraph);
        table.addCell(cell);
        document.add(table);
        document.close();
    }

    public static String wrapperSpan(String paramString)
    {
//        if (paramStyle == null) {
//            return paramString;
//        }
//        FRFont localFRFont = paramStyle.getFRFont();
//        String str = localFRFont.getFontName();
//        int i = localFRFont.getSize();
//        StringBuilder localStringBuilder = new StringBuilder();
//        localStringBuilder.append("<span style='font-size:");
//        localStringBuilder.append(i).append("pt;font-family:").append(str);
//        if (localFRFont.isBold()) {
//            localStringBuilder.append(";font-weight:bold");
//        }
//        if (localFRFont.isItalic()) {
//            localStringBuilder.append(";font-style: italic");
//        }
//        if (localFRFont.isStrikethrough()) {
//            localStringBuilder.append(";text-decoration: line-through;");
//        }
//        localStringBuilder.append(";color:rgb(").append(localFRFont.getForeground().getRed()).append(",").append(localFRFont.getForeground().getGreen()).append(",").append(localFRFont.getForeground().getBlue()).append(")'>");
//        localStringBuilder.append(paramString);
//        localStringBuilder.append("</span>");
//        return localStringBuilder.toString();
        return null;
    }
}