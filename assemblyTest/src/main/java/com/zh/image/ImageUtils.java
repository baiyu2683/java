package com.zh.image;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 图片处理
 * @author Administrator
 * @date 2019/06/06
 */
public class ImageUtils {

    public static byte[] transParentImageBackground(byte[] imageData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        BufferedImage srcImage = ImageIO.read(bais);
        int imageWidth = srcImage.getWidth();
        int imageHeight = srcImage.getHeight();

        BufferedImage destImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = destImage.createGraphics();
///        graphics2D.setComposite(AlphaComposite.Clear);
///        graphics2D.fillRect(0, 0, imageWidth, imageHeight);
///        graphics2D.setComposite(AlphaComposite.Src);
///        graphics2D.fillRect(0, 0, imageWidth, imageHeight);
        graphics2D.drawImage(srcImage,
                0, 0, imageWidth, imageHeight,
                0, 0, imageWidth, imageHeight,
                null);
        int alpha = 0;
        for (int j1 = destImage.getMinY(); j1 < destImage.getHeight(); j1++) {
            for (int j2 = destImage.getMinX(); j2 < destImage.getWidth(); j2++) {
                int rgb = destImage.getRGB(j2, j1);
                int R = (rgb & 0xff0000) >> 16;
                int G = (rgb & 0xff00) >> 8;
                int B = (rgb & 0xff);
                if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                    rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                }
                destImage.setRGB(j2, j1, rgb);
            }
        }
        destImage.flush();
        graphics2D.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(destImage, "png", outputStream);
        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new URL("file:/d:/test1.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageData = ImageUtils.transParentImageBackground(baos.toByteArray());
        IOUtils.write(imageData, new FileOutputStream("d:/test_transparent.png"));
    }
}
