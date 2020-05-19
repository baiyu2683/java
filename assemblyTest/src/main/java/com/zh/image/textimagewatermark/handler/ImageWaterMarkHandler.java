package com.zh.image.textimagewatermark.handler;

import com.zh.image.textimagewatermark.param.ImageLayoutEnum;
import com.zh.image.textimagewatermark.param.ImageWaterMarkParam;
import com.zh.image.textimagewatermark.param.WaterMarkParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * 图片水印处理，和文字拼接
 */
public class ImageWaterMarkHandler implements WaterMarkHandler {

    @Override
    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws Exception {
        ImageWaterMarkParam imageWaterMarkParam = waterMarkParam.getImage();
        ImageLayoutEnum layoutEnum = waterMarkParam.getImageLayoutEnum();

        BufferedImage imageBufferedImage = ImageIO.read(new URL(imageWaterMarkParam.getImagePath()));
        if (src == null) {
            return imageBufferedImage;
        }

        int textImageWidth = src.getWidth();
        int textImageHeight = src.getHeight();

        int imageWidth = imageBufferedImage.getWidth();
        int imageHeight = imageBufferedImage.getHeight();

        int destImageWidth = textImageWidth;
        int destImageHeight = textImageHeight;

        // 对于拼接后的图片，文字图片和当前图片的坐标
        int textX = 0;
        int textY = 0;
        int imageX = 0;
        int imageY = 0;

        if (layoutEnum == ImageLayoutEnum.LEFT) {
            destImageWidth = textImageWidth + imageWidth;
            imageX = 0;
            textX =  imageWidth;

            if (textImageHeight > imageHeight) {
                destImageHeight = textImageHeight;
                textY = 0;
                imageY = (textImageHeight - imageHeight) / 2;
            } else {
                destImageHeight = imageHeight;
                imageY = 0;
                textY = (imageHeight - textImageHeight) / 2;
            }

        } else if (layoutEnum == ImageLayoutEnum.TOP) {
            destImageHeight = textImageHeight + imageHeight;
            imageY = 0;
            textY = imageHeight;

            if (textImageWidth > imageWidth) {
                destImageWidth = textImageWidth;
                textX = 0;
                imageX = (textImageWidth - imageWidth) / 2;
            } else {
                destImageWidth = imageWidth;
                imageX = 0;
                textX = (imageWidth - textImageWidth) / 2;
            }
        } else if (layoutEnum == ImageLayoutEnum.RIGHT) {
            destImageWidth = textImageWidth + imageWidth;
            textX = 0;
            imageX = textImageWidth;

            if (textImageHeight > imageHeight) {
                destImageHeight = textImageHeight;
                textY = 0;
                imageY = (textImageHeight - imageHeight) / 2;
            } else {
                destImageHeight = imageHeight;
                imageY = 0;
                textY = (imageHeight - textImageHeight) / 2;
            }
        } else if (layoutEnum == ImageLayoutEnum.BOTTOM) {
            destImageHeight = textImageHeight + imageHeight;
            textY = 0;
            imageY = textImageHeight;

            if (textImageWidth > imageWidth) {
                destImageWidth = textImageWidth;
                textX = 0;
                imageX = (textImageWidth - imageWidth) / 2;
            } else {
                destImageWidth = imageWidth;
                imageX = 0;
                textX = (imageWidth - textImageHeight) / 2;
            }
        }

        BufferedImage destImage = new BufferedImage(destImageWidth, destImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
        // 透明背景
        Graphics2D graphics2D = destImage.createGraphics();
        destImage = graphics2D.getDeviceConfiguration().createCompatibleImage(destImageWidth, destImageHeight, Transparency.TRANSLUCENT);
        graphics2D.dispose();

        // 图片联合
        graphics2D = destImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(imageBufferedImage, imageX, imageY, imageWidth, imageHeight, null);
        graphics2D.dispose();

        graphics2D = destImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(src, textX, textY,  textImageWidth, textImageHeight, null);
        graphics2D.dispose();

        destImage.flush();
        return destImage;
    }
}
