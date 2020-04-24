package com.zh.itext.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class PdfImageTest {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\test.pdf")));
        document.setPageSize(PageSize.A4);
        document.setMargins(48f, 48f, 72f, 72f);
        document.open();

        float pageBottomMargin = 72f;
        float pageLeftMargin = 48f;

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\htw_2020_02_14_08_57_23.png"));
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();

        float pageHeight = document.getPageSize().getHeight();
        float pageWidth = document.getPageSize().getWidth();

        int hCount = (int) (imageHeight / pageHeight + 1);
        for (int count = 1 ; count <= hCount ; count++) {
            int dx1 = 0;
            int dy1 = (int) (pageHeight * (count - 1));
            int dx2 = (int) pageWidth;
            int dy2 = (int) (dy1 + pageHeight);
            if (dy2 > imageHeight) {
                dy2 = (int) imageHeight;
            }
            Image image = imageCut(bufferedImage,
                    dx1, dy1, dx2, dy2,
                    dx2 - dx1,
                    dy2 - dy1);

            int positionY = px2Pong((int)pageBottomMargin);
            if (count == hCount) {
                positionY = px2Pong((int) (pageHeight - (dy2 - dy1) + pageBottomMargin));
            }
            image.setAbsolutePosition(px2Pong((int)pageLeftMargin), positionY);
            pdfWriter.getDirectContent().addImage(image);
            if (count != hCount) {
                document.newPage();
            }
        }
        document.close();
        pdfWriter.flush();
    }

    private static Image imageCut(BufferedImage srcBufferedImage,
                                  int dx1, int dy1, int dx2, int dy2, int destImageWidth,
                                  int destImageHeight) throws IOException, BadElementException {
        int width = srcBufferedImage.getWidth();
        int height = srcBufferedImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(destImageWidth, destImageHeight, srcBufferedImage.getType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Clear);
        graphics2D.fillRect(0, 0, destImageWidth, destImageHeight);
        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.fillRect(0, 0, destImageWidth, destImageHeight);
//        graphics2D.drawImage(srcBufferedImage,
//                0, 0, destImageWidth, destImageHeight,
//                dx1, dy1, dx2, dy2,
//                null);
        graphics2D.drawImage(srcBufferedImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH),
                0, 0, destImageWidth, destImageHeight,
                dx1, dy1, dx2, dy2,
                null);
        bufferedImage.flush();
        graphics2D.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        byte[] newImageData = outputStream.toByteArray();
        Image newImage = Image.getInstance(newImageData);
        newImage.scaleAbsolute(px2Pong(destImageWidth), px2Pong(destImageHeight));
        return newImage;
    }

    private static int px2Pong(int px) {
        return px * 3 / 4;
    }
}
