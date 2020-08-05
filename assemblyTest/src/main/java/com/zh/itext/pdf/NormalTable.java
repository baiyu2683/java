package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class NormalTable {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test.pdf")));
        document.setPageSize(PageSize.A4);
        document.open();
        document.newPage();

        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{10, 10});

        PdfPCell cell1 = new PdfPCell(new Phrase("1"));
//        cell1.setRowspan(10);
        table.addCell(cell1);

        for (int i = 0 ; i < 10 ; i++) {
            PdfPCell tempCell = new PdfPCell(new Phrase(i + ""));
            tempCell.setFixedHeight(200);
            table.addCell(tempCell);
        }

        document.add(table);
        document.close();
        pdfWriter.flush();
    }
}
