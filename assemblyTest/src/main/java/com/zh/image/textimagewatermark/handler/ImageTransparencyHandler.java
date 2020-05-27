package com.zh.image.textimagewatermark.handler;

import com.zh.image.textimagewatermark.param.WaterMarkParam;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 处理图片透明度，默认百分之50
 */
public class ImageTransparencyHandler implements WaterMarkHandler {

    @Override
    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = destImage.createGraphics();
        float alpha = 127.5f / 255.0f;
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.DST_ATOP, alpha);
        graphics2D.setComposite(alphaComposite);
        graphics2D.drawImage(src, 0, 0, null);
        graphics2D.dispose();
        destImage.flush();
        return destImage;
    }
}
