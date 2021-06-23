package com.zh.image.textimagewatermark.handler;

//import com.zh.image.textimagewatermark.param.WaterMarkParam;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.net.MalformedURLException;

/**
 * 平铺水印
 */
public class TileWaterMarkHandler implements WaterMarkHandler {

//    @Override
//    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws MalformedURLException, Exception {
//        if (src == null) {
//            return null;
//        }
//        int width = waterMarkParam.getWidth();
//        int height = waterMarkParam.getHeight();
//
//        int waterMarkWidth = src.getWidth();
//        int waterMarkHeight =src.getHeight();
//        BufferedImage bufferedImage = new BufferedImage(width + waterMarkWidth, height, BufferedImage.TYPE_4BYTE_ABGR);
//        Graphics2D graphics2D = bufferedImage.createGraphics();
//
//        int startX = waterMarkWidth;
//        int startY = 0;
//        int counter = 0;
//        double constant = 3;
//        while (startY < height) {
//            while (startX < width + waterMarkWidth) {
//                // 图片旋转， 弧度数, 旋转中心点坐标
//                graphics2D = bufferedImage.createGraphics();
//                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//                graphics2D.drawImage(src, startX, startY, waterMarkWidth, waterMarkHeight, null);
//                graphics2D.dispose();
//                startX += waterMarkWidth;
//            }
//            counter++;
//            double percent = (counter % 3) / constant;
//            startX = new Double(percent * waterMarkWidth).intValue();
//            startY += waterMarkHeight;
//        }
//        graphics2D.dispose();
//        bufferedImage.flush();
//        return bufferedImage;
//    }
}
