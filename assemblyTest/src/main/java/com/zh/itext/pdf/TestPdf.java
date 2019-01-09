package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();

        FontFactoryImp fp = new XMLWorkerFontProvider();
        fp.register("E:\\apache-tomcat-6.0.45\\webapps\\HappyServer\\WEB-INF\\classes\\font\\fontResource");
        System.out.println(PageSize.A4.getTop());
        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test1.pdf")));
        document.setPageSize(PageSize.A4);
        document.open();

        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setTotalWidth(new float[]{60});
        table.getDefaultCell().setPadding(1);
        table.setLockedWidth(true);
        table.setSplitLate(false);
        table.setSplitRows(false);
        Font englishFont = fp.getFont("微软雅黑", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        englishFont.setSize(9);
        englishFont.setStyle(com.itextpdf.text.Font.BOLD);
        Chunk english = new Chunk("张恒zhangheng", englishFont);
//        english.setCharacterSpacing(5);
        Phrase phrase = new Phrase();
        phrase.add(english);
        // 首行
        for (int i = 0 ; i < 50 ; i++) {
            PdfPCell cell = new PdfPCell(phrase);
            cell.setBorderWidth(1);
            cell.setBorderColor(BaseColor.BLUE);
            cell.setRowspan(1);
            // 设置leading可以设置行上面的留白，下面无法实现。。。
            cell.setLeading(4, 1);
            table.addCell(cell);
            cell = new PdfPCell(phrase);
            cell.setLeading(1, 1);
            table.addCell(cell);
            table.addCell(cell);
            table.completeRow();
        }
        document.add(table);
        document.close();
        pdfWriter.flush();
    }
}
