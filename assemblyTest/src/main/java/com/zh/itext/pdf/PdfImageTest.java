package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfImageTest {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();

        FontFactoryImp fp = new XMLWorkerFontProvider();
        fp.register("D:\\fonts");

        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("e:/test.pdf")));
        document.setPageSize(PageSize.A10);
        document.setPageSize(PageSize.A4);
        document.setMargins(51f, 51f, 53f, 53f);
        document.open();

        PdfPCell emptyCell = new PdfPCell(new Phrase(" "));
        emptyCell.setPadding(0);

        PdfPTable table = new PdfPTable(1);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setTotalWidth(new float[]{100});
        table.getDefaultCell().setPadding(1);
        table.setLockedWidth(true);
        Font font = fp.getFont("微软雅黑", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font.setSize(9);
        for (int i = 0 ; i < 100 ; i++) {
            String text = i + "";
            String tmpText = getDispText(text);
            Phrase phrase = new Phrase(tmpText, font);
            PdfPCell cell = new PdfPCell(phrase);
            table.addCell(cell);
            table.completeRow();
        }
        table.setTableEvent(new TableSplitEvent());
        document.add(table);
        document.close();
        pdfWriter.flush();
    }

    /**
     * 添加图片
     * @param writer
     * @param document
     */
    private static void addImage(PdfWriter writer, Document document) {
        try {
            Image img = Image.getInstance("D:\\07docx.png");
            img.setAbsolutePosition(100, 100);
            document.add(img);
///            PdfContentByte cont = writer.getDirectContentUnder();
///            cont.addImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @FIXME
     * java8 有sun.font.FontDesignMetrics
     * java11 去掉了...
     * @param text
     * @return
     */
    private static String getDispText(String text) {
        String tmpText = text;
//        java.awt.FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(new java.awt.Font("微软雅黑", java.awt.Font.PLAIN, 9));
//        int w = fm.stringWidth(text);
//        while (w > 120) {
//            tmpText = tmpText.substring(0, tmpText.length() - 1);
//            fm = sun.font.FontDesignMetrics.getMetrics(new java.awt.Font("微软雅黑", java.awt.Font.PLAIN, 9));
//            w = fm.stringWidth(tmpText);
//        }
//        tmpText = tmpText.substring(0, tmpText.length() - 3) + "...";
        return tmpText;
    }
}
