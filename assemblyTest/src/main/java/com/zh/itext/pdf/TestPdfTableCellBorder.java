package com.zh.itext.pdf;

//import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestPdfTableCellBorder {

//    public static void main(String[] args) throws FileNotFoundException, DocumentException {
//        Document document = new Document();
//
//        FontFactoryImp fp = new XMLWorkerFontProvider();
//        fp.register("E:\\apache-tomcat-6.0.45\\webapps\\HappyServer\\WEB-INF\\classes\\font\\fontResource");
//        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test.pdf")));
//        document.setPageSize(PageSize.A4);
//        document.open();
//        document.newPage();
//
//        PdfPCell emptyCell = new PdfPCell(new Phrase("asdfasdf"));
//        emptyCell.setRowspan(2);
//        emptyCell.setPadding(0);
//
//        PdfPTable table = new PdfPTable(1);
//        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        table.setTotalWidth(new float[]{200});
//        table.getDefaultCell().setPadding(1);
//        table.setLockedWidth(true);
//        Font chineseFont = fp.getFont("SimSun", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        chineseFont.setSize(9);
//        chineseFont.setStyle(com.itextpdf.text.Font.BOLD);
//
//        PdfPCell pdfPCell = new PdfPCell(new Phrase("测试", chineseFont));
//        pdfPCell.setCellEvent(new CustomCell());
//        table.addCell(pdfPCell);
//
//        document.add(table);
//        document.close();
//        pdfWriter.flush();
//    }
}

class CustomCell implements PdfPCellEvent {

    @Override
    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
        PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
        cb.saveState();
        cb.setLineWidth(1f);
        cb.setLineDash(new float[] {0f ,0f}, 0);
        cb.moveTo(position.getLeft(), position.getBottom());
        cb.lineTo(position.getRight(), position.getBottom());
        cb.stroke();
        cb.restoreState();
    }
}
