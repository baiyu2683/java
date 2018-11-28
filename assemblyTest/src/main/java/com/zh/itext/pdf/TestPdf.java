package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.hyphenation.Hyphenation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestPdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test.pdf")));
        document.setPageSize(PageSize.A10);
        document.setPageSize(PageSize.A4);
        document.setMargins(51f, 51f, 53f, 53f);
        document.open();
        document.newPage();

        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setSplitLate(false);
        table.setSplitRows(false);
        table.setTotalWidth(new float[]{120, 120});
        table.setLockedWidth(true);
        table.addCell(new Phrase("asdf"));
        table.addCell(new Phrase("1234"));
        table.completeRow();
        document.add(table);

        table = new PdfPTable(5);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setSplitLate(false);
        table.setSplitRows(false);
        table.setTotalWidth(new float[]{100, 100, 100, 100, 50});
        table.setLockedWidth(true);
        table.addCell(new Phrase("1"));
        table.addCell(new Phrase("2"));
        table.addCell(new Phrase("2"));
        table.addCell(new Phrase("2"));
        table.addCell(new Phrase("5"));
        table.completeRow();
        document.add(table);

        document.close();
        pdfWriter.flush();
    }
}
