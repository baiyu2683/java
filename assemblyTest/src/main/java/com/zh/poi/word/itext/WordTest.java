package com.zh.poi.word.itext;

import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.document.output.RtfDataCache;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.table.RtfBorderGroup;
import com.lowagie.text.rtf.table.RtfCell;
import com.lowagie.text.rtf.table.RtfRow;

import java.awt.*;
import java.io.*;

public class WordTest {

    public static void main(String[] args) throws Exception {
        Document document = new Document();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\test1.docx"));
        RtfWriter2 rtfWriter2 = RtfWriter2.getInstance(document, fileOutputStream);
        rtfWriter2.getDocumentSettings()
                .setDataCacheStyle(RtfDataCache.CACHE_DISK);
        document.open();

        float width = 595f;
        float height = 842f;
        float marginLeft = 36f;
        float marginRight = 36f;
        float marginTop = 36f;
        float marginBottom = 36f;

        Rectangle page = new Rectangle(width, height);
        document.setPageSize(page);
        document.setMargins(marginLeft, marginRight, marginTop, marginBottom);

        Table table = new Table(10, 10);
        table.setSpacing(0);
        table.setPadding(0);
        table.setAutoFillEmptyCells(true);
        table.setAlignment(Table.ALIGN_CENTER);
        table.setAutoFillEmptyCells(true);
        table.setDefaultCell(createDefaultCell(Color.BLACK));
        table.setWidth(100);

        float[] widths = new float[10];
        for (int i = 0 ; i < 10 ; i++) {
            widths[i] = 20;
        }
        table.setWidths(widths);

        Row row = table.getRow(0);
        row.setHeight(100);

        RtfCell tableCell = new RtfCell();
        Paragraph paragraph = new Paragraph();
        Chunk chunk = new Chunk("asdf");
        paragraph.add(chunk);
        tableCell.add(paragraph);

        table.addCell(tableCell);

        document.add(table);
        document.close();
        rtfWriter2.close();
        fileOutputStream.close();
    }

    private static Cell createDefaultCell(Color paramColor) throws Exception {
        String fontName = "宋体";
        try {
            fontName = new String(fontName.getBytes("gb18030"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        RtfFont rtfFont = new RtfFont(fontName);
        rtfFont.setSize(9);
        rtfFont.setCharset(134);
        RtfCell localRtfCell = new RtfCell(new Paragraph("", rtfFont));
//        localRtfCell.setBackgroundColor(paramColor);
        RtfBorderGroup localRtfBorderGroup = new RtfBorderGroup();
        localRtfBorderGroup.addBorder(1, 1, 1.0F, paramColor);
        localRtfBorderGroup.addBorder(8, 1, 1.0F, paramColor);
        localRtfBorderGroup.addBorder(2, 1, 1.0F, paramColor);
        localRtfBorderGroup.addBorder(4, 1, 1.0F, paramColor);
        localRtfCell.setBorders(localRtfBorderGroup);
        return localRtfCell;
    }

}
