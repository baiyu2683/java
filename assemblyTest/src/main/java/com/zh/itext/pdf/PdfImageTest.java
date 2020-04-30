package com.zh.itext.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;

public class PdfImageTest {

    private static final Logger logger = LoggerFactory.getLogger(PdfImageTest.class);

    private ByteArrayOutputStream os;

    private Document document;

    private PdfWriter pdfWriter;

    private int scale = 1;

    public static void main(String[] args) throws IOException {
        PdfImageTest pdfImageTest = new PdfImageTest();
        byte[] file = pdfImageTest.exporter();
        if (file == null) {
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.pdf");
        IOUtils.write(file, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private byte[] getImgData() {
        try {
            return IOUtils.toByteArray(new FileInputStream("C:\\Users\\Administrator\\Desktop\\test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] exporter() {
        try {
            Paper paper = new Paper();
            paper.setHeight(931 * scale);
            paper.setWidth(649 * scale);
            paper.setMarginLeft(72);
            paper.setMarginRight(72);
            paper.setMarginTop(96);
            paper.setMarginBottom(96);

            init(paper);
            document.open();
            addImg(paper);
            document.close();
            return os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导出pdf失败", e);
        } finally {
            os = null;
        }
        return null;
    }

    /**
     * 设置纸张
     */
    private void init(Paper paper) {
        if (paper != null) {
            float realPageWidth = paper.getMarginLeft() + paper.getWidth() + paper.getMarginRight();
            float realPageHeight = paper.getMarginTop() + paper.getHeight() + paper.getMarginBottom();
            Rectangle pageSize = new Rectangle(px2Pong(realPageWidth), px2Pong(realPageHeight));
            document = new Document(pageSize, px2Pong(paper.getMarginLeft()), px2Pong(paper.getMarginRight()),
                    px2Pong(paper.getMarginTop()), px2Pong(paper.getMarginBottom()));
        } else {
            document = new Document();
        }
        this.os = new ByteArrayOutputStream();
        try {
            pdfWriter = PdfWriter.getInstance(document,os);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一张图片
     * @throws DocumentException
     */
    private void addImg(Paper paper) {
        try {
            float pageHeight = paper.getHeight();
            float pageWidth = paper.getWidth();
            float pageLeftMargin = paper.getMarginLeft();
            float pageBottomMargin = paper.getMarginBottom();
            // 根据imgKey获取图片数据
            byte[] imgData = getImgData();
            if (ArrayUtils.isEmpty(imgData)) {
                return;
            }
            BufferedImage srcImage = ImageIO.read(new ByteArrayInputStream(imgData));
            float imageHeight = srcImage.getHeight();
            float imageWidth = srcImage.getWidth();
            if (imageHeight > pageHeight) {
                int hCount = (int) (imageHeight / pageHeight + 1);
                for (int count = 1 ; count <= hCount ; count++) {
                    int dx1 = 0;
                    int dy1 = (int) (pageHeight * (count - 1));
                    int dx2 = (int) pageWidth;
                    int dy2 = (int) (dy1 + pageHeight);
                    if (dy2 > imageHeight) {
                        dy2 = (int) imageHeight;
                    }
                    Image image = imageCut(srcImage,
                            dx1, dy1, (int)pageWidth, dy2,
                            dx2 - dx1,
                            dy2 - dy1);
                    float positionY = px2Pong(pageBottomMargin);
                    if (count == hCount) {
                        positionY = px2Pong((pageHeight - (dy2 - dy1) + pageBottomMargin));
                    }
                    image.setAbsolutePosition(px2Pong(pageLeftMargin), positionY);
                    pdfWriter.getDirectContent().addImage(image);
                    if (count != hCount) {
                        document.newPage();
                    }
                }
            } else {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(srcImage, "png", baos);
                Image image = Image.getInstance(baos.toByteArray());
                image.scaleAbsoluteWidth(px2Pong(imageWidth));
                image.scaleAbsoluteHeight(px2Pong(imageHeight));
                image.setAbsolutePosition(px2Pong(pageLeftMargin),
                        px2Pong((int) (pageHeight - imageHeight + pageBottomMargin)));
                pdfWriter.getDirectContent().addImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Image imageCut(BufferedImage srcBufferedImage,
                                  int dx1, int dy1, int dx2, int dy2, int destImageWidth,
                                  int destImageHeight) throws IOException, BadElementException,
            MalformedURLException {
        BufferedImage bufferedImage = new BufferedImage(destImageWidth, destImageHeight, srcBufferedImage.getType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Clear);
        graphics2D.fillRect(0, 0, destImageWidth, destImageHeight);
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.fillRect(0, 0, destImageWidth, destImageHeight);
        graphics2D.drawImage(srcBufferedImage,
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

    private static float px2Pong(float px) {
        return px * 72 / 96;
    }
}
