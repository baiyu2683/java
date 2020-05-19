package com.zh.image.textimagewatermark.handler;

import com.zh.image.textimagewatermark.param.TextWaterMarkParam;
import com.zh.image.textimagewatermark.param.WaterMarkParam;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.net.MalformedURLException;

/**
 * 平铺水印
 */
public class TileWaterMarkHandler implements WaterMarkHandler {

    @Override
    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws MalformedURLException, Exception {
        int angle = waterMarkParam.getAngle();
        int width = waterMarkParam.getWidth();
        int height = waterMarkParam.getHeight();

        int horizontalSpacing = waterMarkParam.getHorizontalSpacing();
        int vertinalSpacing = waterMarkParam.getVerticalSpacing();

        int waterMarkWidth = src.getWidth();
        int waterMarkHeight =src.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        // 加背景色
//        graphics2D .setColor(Color.WHITE);
//        graphics2D.fillRect(0, 0, width, height);
//        graphics2D.dispose();

//        graphics2D = bufferedImage.createGraphics();


        int startX = 0;
        int startY = 0;
        boolean exchange = true;
        while (startY < height) {
            while (startX < width + waterMarkWidth) {
                // 图片旋转， 弧度数, 旋转中心点坐标
                graphics2D = bufferedImage.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                graphics2D.rotate(Math.toRadians(-angle), startX + waterMarkWidth / 2, startY + waterMarkHeight / 2);
                graphics2D.drawImage(src, startX, startY, waterMarkWidth, waterMarkHeight, null);
                graphics2D.dispose();
                startX += waterMarkWidth + horizontalSpacing / 2;
            }
            if (exchange) {
                startX = waterMarkWidth / 2;
            } else {
                startX = 0;
            }
            exchange = !exchange;
            startY += waterMarkHeight + vertinalSpacing;
        }
        graphics2D.dispose();
        bufferedImage.flush();
        return bufferedImage;
    }

    private int caculateTextWidth(FontDesignMetrics metrics, String content) {
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }
}
