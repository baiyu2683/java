package com.zh.image.textimagewatermark.handler;

//import com.zh.image.textimagewatermark.param.WaterMarkParam;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.net.MalformedURLException;

/**
 * 水印旋转
 */
public class RotateWaterMarkHandler implements WaterMarkHandler {

//    @Override
//    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws MalformedURLException, Exception {
//        if (src == null) {
//            return null;
//        }
//        int srcWidth = src.getWidth();
//        int srcHeight = src.getHeight();
//
//        int horizontalSpacing = waterMarkParam.getHorizontalSpacing();
//        int verticalSpacing = waterMarkParam.getVerticalSpacing();
//
//        // TODO 倾斜后图片宽高计算，根据布局不同有不同
//        int angle = waterMarkParam.getAngle();
//        double radian = Math.toRadians(angle);
//        double rotateImageWidth = srcWidth * Math.abs(Math.cos(radian))
//                + srcHeight * Math.abs(Math.sin(radian)) + horizontalSpacing;
//        double rotateImageHeight = srcWidth * Math.abs(Math.sin(radian))
//                + srcWidth * Math.abs(Math.cos(radian)) + verticalSpacing;
//
//        BufferedImage rotateImage = new BufferedImage(new Double(rotateImageWidth).intValue(), new Double(rotateImageHeight).intValue(), BufferedImage.TYPE_4BYTE_ABGR);
//        Graphics2D graphics2D = rotateImage.createGraphics();
//        rotateImage = graphics2D.getDeviceConfiguration().createCompatibleImage(rotateImage.getWidth(), rotateImage.getHeight(), Transparency.TRANSLUCENT);
//        graphics2D.dispose();
//
////        graphics2D = rotateImage.createGraphics();
////        graphics2D.setColor(Color.BLACK);
////        graphics2D.fillRect(0, 0, (int)rotateImageWidth, (int)rotateImageHeight);
////        graphics2D.dispose();
//
//        graphics2D = rotateImage.createGraphics();
//        // 画倾斜图
//        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        // TODO 根据角度不同，需要计算中心点
//        graphics2D.rotate(-radian, rotateImageWidth / 2, rotateImageHeight / 2);
//        graphics2D.drawImage(src, (int)(rotateImageWidth - srcWidth) / 2, (int)(rotateImageHeight - srcHeight) / 2, null);
//        graphics2D.dispose();
//
//        rotateImage.flush();
//        return rotateImage;
//    }
}
