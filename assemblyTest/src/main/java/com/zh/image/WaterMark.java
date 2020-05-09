package com.zh.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 测试图片加文字水印
 */
public class WaterMark {

    public static void main(String[] args) {
        String imagePath = "file:\\C:\\Users\\Administrator\\Desktop\\1.jpg";
        String text = "Bing";
        WaterMark waterMark = new WaterMark();
        try {
//            BufferedImage bufferedImage = waterMark.addWaterMark(imagePath, text);
            BufferedImage bufferedImage = waterMark.waterMarkImage(text);
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\2.jpg");
            ImageIO.write(bufferedImage, "png", fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("完成...");
    }

    /**
     * @param imagePath 图片路径
     * @param text 文字
     * @return
     */
    public BufferedImage addWaterMark(String imagePath, String text) throws IOException {
        try {
            BufferedImage srcImage = ImageIO.read(new URL(imagePath));
            int width = srcImage.getWidth();
            int height = srcImage.getHeight();
            int imageType = srcImage.getType();

            // 画原图
            BufferedImage destImage = new BufferedImage(width, height, imageType);
            Graphics2D graphics2D = destImage.createGraphics();
            graphics2D.setComposite(AlphaComposite.Src);
            graphics2D.fillRect(0, 0, width, height);

            graphics2D.drawImage(srcImage,
                    0, 0 , width, height,
                    0, 0, width, height,
                    null);

            // 画文字水印
            // 设置文字边缘抗锯齿
            graphics2D.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // 设置水印文字透明度
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
            // 文字旋转
//            graphics2D.rotate(Math.toRadians(-45), (double) width / 2, (double) height / 2);
            // 文字颜色
            graphics2D.setColor(Color.WHITE);
            // 文字字体
            graphics2D.setFont(new Font("微软雅黑", Font.PLAIN, 50));
            graphics2D.drawString(text, 1460, 980);
            graphics2D.dispose();

            graphics2D = destImage.createGraphics();
            // 设置文字边缘抗锯齿
            graphics2D.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // 设置水印文字透明度
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            // 文字旋转
//            graphics2D.rotate(Math.toRadians(-45), (double) width / 2, (double) height / 2);
            // 文字颜色
            graphics2D.setColor(Color.BLACK);
            // 文字字体
            graphics2D.setFont(new Font("微软雅黑", Font.PLAIN, 50));
            graphics2D.drawString(text, 460, 980);
            graphics2D.dispose();

            destImage.flush();

            return destImage;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * jpg不支持透明背景
     * 需要用png格式
     * @param text
     * @return
     */
    public BufferedImage waterMarkImage(String text) {
        int width = 1000;
        int height = 1000;
        int imageType = BufferedImage.TYPE_4BYTE_ABGR;

        // 画原图
        BufferedImage destImage = new BufferedImage(width, height, imageType);
        Graphics2D graphics2D = destImage.createGraphics();

        destImage = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics2D.dispose();
        graphics2D = destImage.createGraphics();

        // 设置文字边缘抗锯齿
        graphics2D.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 设置水印文字透明度
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.5f));

        graphics2D.setColor(new Color(255, 0, 0));
        graphics2D.setFont(new Font("微软雅黑", Font.PLAIN, 50));
        graphics2D.drawString(text, 500, 200);
        graphics2D.dispose();
        destImage.flush();
        return destImage;
    }
}
