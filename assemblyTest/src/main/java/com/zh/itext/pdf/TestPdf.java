package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.hyphenation.Hyphenation;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();

        FontFactoryImp fp = new XMLWorkerFontProvider();
        fp.register("D:\\fonts");

        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test.pdf")));
        document.setPageSize(PageSize.A10);
        document.setPageSize(PageSize.A4);
        document.setMargins(51f, 51f, 53f, 53f);
        document.open();
        document.newPage();

        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setTotalWidth(new float[]{120, 120});
        table.getDefaultCell().setPadding(10);
        table.setLockedWidth(true);
        Font font = fp.getFont("微软雅黑", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font.setSize(9);
        String text = "asdfasdfasdfasdfasdfasdfasdfasdfasdf";
        String tmpText = text;
        java.awt.FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(new java.awt.Font("微软雅黑", java.awt.Font.PLAIN, 9));
        int w = fm.stringWidth(text);
        while (w > 120) {
            tmpText = tmpText.substring(0, tmpText.length() - 1);
            fm = sun.font.FontDesignMetrics.getMetrics(new java.awt.Font("微软雅黑", java.awt.Font.PLAIN, 9));
            w = fm.stringWidth(tmpText);
        }
        tmpText = tmpText.substring(0, tmpText.length() - 3) + "...";
        Phrase phrase = new Phrase(tmpText, font);
        PdfPCell cell = new PdfPCell(phrase);
//        cell.setPadding(10);
        cell.setNoWrap(true);
        table.addCell(cell);
        table.addCell(cell);
        table.completeRow();
        document.add(table);
        document.close();
        pdfWriter.flush();
    }
}
