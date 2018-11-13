package com.zh.itext.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestPdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test.pdf")));

        document.open();
        document.newPage();

        PdfPTable table = new PdfPTable(2);
        table.setSplitLate(false);
        table.setSplitRows(false);
        table.setTotalWidth(new float[]{250, 250});
        table.setLockedWidth(true);
        for (int i = 0 ; i < 15 ; i++) {
            Chunk chunk = new Chunk(i + "asdfsasfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf");
            Paragraph paragraph = new Paragraph("iasddddddasdfasdfasdfdssssddddffffqqqqwwwweeeerrrr");
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.addElement(paragraph);
            pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            pdfPCell.setFixedHeight(30);
            if (i == 0) {
                pdfPCell.setRowspan(12);
                pdfPCell.setBorderColor(BaseColor.BLUE);
            }
            table.addCell(pdfPCell);
            if (i >= 1) {
                table.completeRow();
            }
        }
        document.add(table);
        document.close();
        pdfWriter.flush();
    }
}
