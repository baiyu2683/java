package com.zh.itext.pdf;

import com.aspose.words.HorizontalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.lowagie.text.HeaderFooter;
import com.sun.javafx.iio.ImageLoaderFactory;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date  2019/06/06
 */
public class TestPdf {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("d:/test1.pdf")));
        pdfWriter.setPageEvent(new PageEvent("智能报告", "发布人: 测试用户", "发布时间: 2019年06月10日"));
        Rectangle pageSize = new Rectangle(595, 842);
        document.setPageSize(pageSize);

        document.open();

        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setTotalWidth(new float[]{100, 100});
        table.getDefaultCell().setPadding(1);
        table.setLockedWidth(true);
        table.setSplitLate(false);
        table.setSplitRows(false);

        PdfPCellEvent event = getCellBackGroundImageEvent();

        PdfPCell cell11 = new PdfPCell();
        cell11.setCellEvent(event);
        cell11.setHorizontalAlignment(HorizontalAlignment.LEFT);
        cell11.setFixedHeight(100);
        cell11.setBorderWidth(0);
//        cell11.setBorderColor(BaseColor.BLUE);
        table.addCell(cell11);

        PdfPCell cell12 = new PdfPCell();
        cell12.setFixedHeight(100);
        cell12.setBorderWidth(0);
//        cell12.setBorderColor(BaseColor.BLUE);
        cell12.setCellEvent(event);
        table.addCell(cell12);
        table.completeRow();
        document.add(table);

        document.newPage();

        PdfPTable table2 = new PdfPTable(2);
        table2.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table2.setTotalWidth(new float[]{100, 100});
        table2.getDefaultCell().setPadding(1);
        table2.setLockedWidth(true);
        table2.setSplitLate(false);
        table2.setSplitRows(false);

        PdfPCell cell21 = new PdfPCell();
        cell21.setCellEvent(event);
        cell21.setHorizontalAlignment(HorizontalAlignment.LEFT);
        cell21.setFixedHeight(100);
        cell21.setBorderWidth(2);
        cell21.setBorderColor(BaseColor.BLUE);
        table.addCell(cell21);

        PdfPCell cell22 = new PdfPCell();
        cell22.setFixedHeight(100);
        cell22.setBorderWidth(2);
        cell22.setBorderColor(BaseColor.BLUE);
        cell22.setCellEvent(event);
        table2.addCell(cell22);
        table2.completeRow();
        document.add(table2);

        document.close();
        pdfWriter.flush();
    }

    private static PdfPCellEvent getCellBackGroundImageEvent() {
        return (cell, position, canvases) -> {
            System.out.println("=====================");
            System.out.println(position.getLeft() + "-" + position.getTop());
//            try {
//                File file = new File("d:/test2.png");
//                System.out.println(file.toURI().toURL());
//                BufferedImage srcImage = ImageIO.read(file.toURI().toURL());
//
//                int cellWidth = (int) position.getWidth();
//                int cellHeight = (int) position.getHeight();
//                int imageWidth = srcImage.getWidth();
//                int imageHeight = srcImage.getHeight();
//
//                BufferedImage destImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
//                Graphics2D graphics2D = destImage.createGraphics();
//                graphics2D.setComposite(AlphaComposite.Clear);
//                graphics2D.fillRect(0, 0, imageWidth, imageHeight);
//                graphics2D.setComposite(AlphaComposite.Src);
//                graphics2D.fillRect(0, 0, imageWidth, imageHeight);
//                graphics2D.drawImage(srcImage,
//                        0, imageHeight / 2, cellWidth / 2, imageHeight,
//                        0, imageHeight / 2, cellWidth / 2, imageHeight,
//                        null);
//                destImage.flush();
//                graphics2D.dispose();
//
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                ImageIO.write(destImage, "png", outputStream);
//                byte[] newImageData = outputStream.toByteArray();
//                IOUtils.write(newImageData, new FileOutputStream("d:/test_result.png"));
//                outputStream = null;
//                Image newImage = Image.getInstance(newImageData);
//                newImage.setAbsolutePosition(position.getLeft(), position.getBottom());
//                canvases[PdfPTable.BACKGROUNDCANVAS]
//                        .addImage(newImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (BadElementException e) {
//                e.printStackTrace();
//            } catch (DocumentException e) {
//                e.printStackTrace();
//            }
        };
    }

    private static void longImage() throws IOException {
        BufferedImage srcImage = ImageIO.read(new URL("file:/d:/test1.png"));
        BufferedImage destImage = new BufferedImage(srcImage.getWidth(), 10000, srcImage.getType());
        Graphics2D graphics2D = destImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Clear);
        graphics2D.fillRect(0, 0, destImage.getWidth(), destImage.getHeight());
        graphics2D.setComposite(AlphaComposite.Src);

        int hCount = (destImage.getHeight() / srcImage.getHeight()) + 1;
        for (int h = 0 ; h < hCount ; h++) {
            int dx1 = 0;
            int dy1 = srcImage.getHeight() * h;
            int dx2 = srcImage.getWidth();
            int dy2 = dy1 + srcImage.getHeight();
            if (dy2 > destImage.getHeight()) {
                dy2 = destImage.getHeight();
            }
            graphics2D.drawImage(srcImage,
                    dx1, dy1, dx2, dy2,
                    0, 0, dx2 - dx1, dy2 - dy1,
                    null);
        }
        destImage.flush();
        graphics2D.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(destImage, "png", baos);
        IOUtils.write(baos.toByteArray(), new FileOutputStream("d://longpic.png"));
    }

}
