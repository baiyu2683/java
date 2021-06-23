package com.zh.image;

import com.alibaba.fastjson.JSON;
import com.zh.image.textimagewatermark.WaterMarkService;
import com.zh.image.textimagewatermark.param.ImageLayoutEnum;
import com.zh.image.textimagewatermark.param.ImageWaterMarkParam;
import com.zh.image.textimagewatermark.param.TextWaterMarkParam;
import com.zh.image.textimagewatermark.param.WaterMarkParam;
import org.apache.commons.io.IOUtils;
//import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 测试图片加文字水印
 */
public class WaterMark {

    public static void main(String[] args) {
//        long time = 0;
//        for (int i = 0 ; i < 1 ; i++) {
//            long start = System.currentTimeMillis();
//            WaterMarkService waterMarkService = new WaterMarkService();
//
//            WaterMarkParam waterMarkParam = new WaterMarkParam();
//            waterMarkParam.setHeight(842);
//            waterMarkParam.setWidth(595);
//            waterMarkParam.setAngle(45);
//            waterMarkParam.setHorizontalSpacing(60);
//            waterMarkParam.setVerticalSpacing(30);
//            waterMarkParam.setImageLayoutEnum(ImageLayoutEnum.LEFT);
//
//            TextWaterMarkParam textWaterMarkParam = new TextWaterMarkParam();
//            textWaterMarkParam.setColor("#909090");
//            textWaterMarkParam.setContent("未配置水印");
//            textWaterMarkParam.setFontFamily("微软雅黑");
//            textWaterMarkParam.setFontSize(60);
//            waterMarkParam.setText(textWaterMarkParam);
//
//            ImageWaterMarkParam imageWaterMarkParam = new ImageWaterMarkParam();
//            imageWaterMarkParam.setDescription("图片水印");
//            imageWaterMarkParam.setName("hhhh");
//            imageWaterMarkParam.setImagePath("file:\\D:\\company\\乐创者\\水印\\lcz.png");
//            waterMarkParam.setImage(imageWaterMarkParam);
//
//            try {
//                byte[] bytes = waterMarkService.getWaterMarkImage(waterMarkParam);
//                time += System.currentTimeMillis() - start;
//                FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\2.jpg");
//                IOUtils.write(bytes, fileOutputStream);
//                fileOutputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(time);
    }

    /**
     * @param imagePath 图片路径
     * @param text 文字
     * @return
     */
//    public BufferedImage addWaterMark(String imagePath, String text) throws IOException {
//        try {
//            BufferedImage srcImage = ImageIO.read(new URL(imagePath));
//            int width = srcImage.getWidth();
//            int height = srcImage.getHeight();
//            int imageType = srcImage.getType();
//
//            // 画原图
//            BufferedImage destImage = new BufferedImage(width, height, imageType);
//            Graphics2D graphics2D = destImage.createGraphics();
//            graphics2D.setComposite(AlphaComposite.Src);
//            graphics2D.fillRect(0, 0, width, height);
//
//            graphics2D.drawImage(srcImage,
//                    0, 0 , width, height,
//                    0, 0, width, height,
//                    null);
//
//            // 画文字水印
//            // 设置文字边缘抗锯齿
//            graphics2D.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            // 设置水印文字透明度
//            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
//            // 文字旋转
////            graphics2D.rotate(Math.toRadians(-45), (double) width / 2, (double) height / 2);
//            // 文字颜色
//            graphics2D.setColor(Color.WHITE);
//            // 文字字体
//            graphics2D.setFont(new Font("微软雅黑", Font.PLAIN, 50));
//            graphics2D.drawString(text, 1460, 980);
//            graphics2D.dispose();
//
//            graphics2D = destImage.createGraphics();
//            // 设置文字边缘抗锯齿
//            graphics2D.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            // 设置水印文字透明度
//            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//            // 文字旋转
////            graphics2D.rotate(Math.toRadians(-45), (double) width / 2, (double) height / 2);
//            // 字体旋转
//
//            Font font = new Font("微软雅黑", Font.PLAIN, 50);
//            AffineTransform affineTransform = new AffineTransform();
//            affineTransform.rotate(Math.toRadians(0), 0, 0);
//            Font rotatedFont = font.deriveFont(affineTransform);
//            // 文字颜色
//            graphics2D.setColor(Color.BLACK);
//            // 文字字体
//            graphics2D.setFont(rotatedFont);
//            graphics2D.drawString(text, 460, 980);
//            graphics2D.dispose();
//
//            destImage.flush();
//
//            return destImage;
//        } catch (IOException e) {
//            throw e;
//        }
//    }
//
//    /**
//     * jpg不支持透明背景
//     * 需要用png格式
//     * @param text
//     * @return
//     */
//    public BufferedImage waterMarkImage(String text) {
//        int width = 595;
//        int height = 842;
//        int imageType = BufferedImage.TYPE_4BYTE_ABGR;
//
//        // 画原图
//        BufferedImage destImage = new BufferedImage(width, height, imageType);
//        Graphics2D graphics2D = destImage.createGraphics();
//
//        destImage = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//        graphics2D.dispose();
//        graphics2D = destImage.createGraphics();
//
//        // 设置文字边缘抗锯齿
//        graphics2D.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
////        graphics2D.rotate(70, 0, 50);
//        // 设置水印文字透明度
////        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.5f));
//        graphics2D.setColor(Color.BLACK);
//
//        // 字体旋转
//        Font font = new Font("微软雅黑", Font.PLAIN, 12);
//        AffineTransform affineTransform = new AffineTransform();
//        affineTransform.rotate(Math.toRadians(45), 0, 0);
//        Font rotatedFont = font.deriveFont(affineTransform);
//
//        graphics2D.setFont(rotatedFont);
//        graphics2D.drawString(text, 400, 800);
//        graphics2D.dispose();
//        destImage.flush();
//        return destImage;
//    }
//
//
//    /**
//     * 图片和文字拼接后，旋转一定角度，生成一整张水印
//     * @param imagePath
//     * @param text
//     * @return
//     */
//    public BufferedImage waterMarkTextImageRotate(String imagePath, String text) {
//        BufferedImage waterMark = waterMarkTextImage(imagePath, text);
//
//        BufferedImage bufferedImage = new BufferedImage(1024, 768, BufferedImage.TYPE_4BYTE_ABGR);
//        Graphics2D graphics2D = bufferedImage.createGraphics();
//        graphics2D.setColor(Color.RED);
//        graphics2D.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
//        int waterMarkWidth = waterMark.getWidth();
//        int waterMarkHeight =waterMark.getHeight();
//
//        // 图片旋转， 弧度数, 旋转中心点坐标
//        graphics2D.rotate(Math.toRadians(-30), waterMarkWidth / 2, waterMarkHeight / 2);
//        graphics2D.drawImage(waterMark, 0, 0, waterMarkWidth, waterMarkHeight, null);
//        graphics2D.dispose();
//
//        graphics2D = bufferedImage.createGraphics();
//        graphics2D.rotate(Math.toRadians(-30), waterMarkWidth + waterMarkWidth / 2, waterMarkHeight / 2);
//        graphics2D.drawImage(waterMark, waterMarkWidth + 10, 0, waterMarkWidth, waterMarkHeight, null);
//        graphics2D.dispose();
//
//        graphics2D = bufferedImage.createGraphics();
//        graphics2D.rotate(Math.toRadians(-30), waterMarkWidth / 2, waterMarkHeight + waterMarkHeight / 2);
//        graphics2D.drawImage(waterMark, 0, waterMarkHeight + 50, waterMarkWidth, waterMarkHeight, null);
//        graphics2D.dispose();
//
//        bufferedImage.flush();
//        return bufferedImage;
//    }
//
//    /**
//     * 图片和文字拼接
//     * @param imagePath
//     * @param text
//     * @return
//     */
//    public BufferedImage waterMarkTextImage(String imagePath, String text) {
//        //1. 文字转图片
//        BufferedImage textBufferedImage =  textToImage(text);
//
//        int textImageWidth = textBufferedImage.getWidth();
//        int textImageHeight = textBufferedImage.getHeight();
//
//        int destImageWidth = textImageWidth;
//        int destImageHeight = textImageHeight;
//        //2. 图片和文字联合
//        try {
//            BufferedImage imageBufferedImage = ImageIO.read(new URL(imagePath));
//
//            // TODO 根据布局计算, 例子默认左右，并且不重叠
//            int imageWidth = imageBufferedImage.getWidth();
//            int imageHeight = imageBufferedImage.getHeight();
//
//            destImageHeight = textImageHeight > imageHeight ? textImageHeight :imageHeight;
//            destImageWidth += imageWidth;
//
//            BufferedImage destImage = new BufferedImage(destImageWidth, destImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
//            // 透明背景
//            Graphics2D graphics2D = destImage.createGraphics();
//            destImage = graphics2D.getDeviceConfiguration().createCompatibleImage(destImageWidth, destImageHeight, Transparency.TRANSLUCENT);
//            graphics2D.dispose();
//
//            // 填充背景
////            graphics2D = destImage.createGraphics();
////            graphics2D.setColor(Color.BLACK);
////            graphics2D.fillRect(0, 0, destImageWidth, destImageHeight);
////            graphics2D.dispose();
//
//            // 放置文字图片
//            graphics2D = destImage.createGraphics();
//            graphics2D.drawImage(textBufferedImage, imageWidth, imageHeight / 2,  textImageWidth, textImageHeight, null);
//            graphics2D.dispose();
//
//            // 放置图片
//            graphics2D = destImage.createGraphics();
//            graphics2D.drawImage(imageBufferedImage, 0, 0, imageWidth, imageHeight, null);
//            graphics2D.dispose();
//
//            destImage.flush();
//            return destImage;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 文字变成等高等宽图片
//     * @param text
//     * @return
//     */
//    public BufferedImage textToImage(String text) {
//        Font font = new Font("微软雅黑", Font.PLAIN, 16);
//
//        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
//        int width = caculateTextWidth(metrics, text);
//        int height = metrics.getHeight();
//
//        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//        Graphics2D graphics2D = destImage.createGraphics();
//
//        // 设置文字边缘抗锯齿
////        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
////        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
////        // 设置水印文字透明度
////        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.5f));
//
//        graphics2D.setColor(Color.GRAY);
//        graphics2D.setFont(font);
//        graphics2D.drawString(text, 0, metrics.getAscent());
//
//        graphics2D.dispose();
//        destImage.flush();
//
//        return destImage;
//    }
//
//    public static int caculateTextWidth(FontDesignMetrics metrics, String content) {
//        int width = 0;
//        for (int i = 0; i < content.length(); i++) {
//            width += metrics.charWidth(content.charAt(i));
//        }
//        return width;
//    }
}
