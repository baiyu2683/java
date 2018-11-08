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
        for (int i = 0 ; i < 15 ; i++) {
            Chunk chunk = new Chunk(i + "张张张张张张张张张张张张张张张张张张张张张张张张张张张张张");
            PdfPCell pdfPCell = new PdfPCell(new Phrase(chunk));
            pdfPCell.setFixedHeight(90);
            if (i == 0) {
                pdfPCell.setRowspan(12);
                pdfPCell.setBorderColor(BaseColor.BLUE);
//                pdfPCell.setFixedHeight(1000);
            }
            table.addCell(pdfPCell);
            if (i >= 1) {
                table.completeRow();
            }
        }
        document.add(table);

        // 复制一个
//        PdfPTable newTable = new PdfPTable(2);
//        for (int i = 0 ; i < 7 ; i++) {
//            PdfPRow row = table.getRow(i);
//            for (PdfPCell cell : row.getCells()) {
//                if (cell != null) {
//                    newTable.addCell(cell);
//                } else {
//
//                }
//            }
//            newTable.completeRow();
//        }
//        document.add(newTable);
////        document.newPage();
//        newTable = new PdfPTable(2);
//        for (int i = 7 ; i < 8 ; i++) {
//            PdfPRow row = table.getRow(i);
//            for (PdfPCell cell : row.getCells()) {
//                if (cell != null) newTable.addCell(cell);
//            }
//            newTable.completeRow();
//        }
//        document.add(newTable);

        document.close();
        pdfWriter.flush();
    }
}
