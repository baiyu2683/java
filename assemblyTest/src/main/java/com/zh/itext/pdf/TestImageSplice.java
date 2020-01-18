package com.zh.itext.pdf;

import com.itextpdf.text.BadElementException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestImageSplice {

    public static void main(String[] args) throws IOException, BadElementException {
        File imageFile = new File("d:/test4.png");
        com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imageFile.toURI().toURL());

        int width = (int) image.getScaledWidth();
        int height = (int) image.getScaledHeight();

//        com.lowagie.text.Image image1 = com.lowagie.text.Image.getInstance();
        BufferedImage srcBufferedImage = ImageIO.read(imageFile.toURI().toURL());

        BufferedImage bufferedImage = new BufferedImage(177, 100, image.getOriginalType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        GraphicsConfiguration graphicsConfiguration = graphics2D.getDeviceConfiguration();
//        bufferedImage = graphicsConfiguration
//                .createCompatibleImage(width, height, Transparency.BITMASK);
        graphics2D.setBackground(Color.WHITE);
        graphics2D.fillRect(0, 0, 177, 100);
//        graphics2D.drawImage(srcBufferedImage, 0, 0,  width, height, null);
        System.out.println(srcBufferedImage.getWidth());
        System.out.println(srcBufferedImage.getHeight());
        graphics2D.drawImage(srcBufferedImage,
                0,0, 177, 100,
                0, 0, 100, 50,
                null);
        bufferedImage.flush();
        graphics2D.dispose();

        ImageIO.write(bufferedImage, "png", new FileOutputStream("d:/test4_sub.png"));
    }
}
