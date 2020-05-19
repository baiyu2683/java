package com.zh.image.textimagewatermark.handler;

import com.zh.image.textimagewatermark.param.TextWaterMarkParam;
import com.zh.image.textimagewatermark.param.WaterMarkParam;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextWaterMarkHandler implements WaterMarkHandler {

    @Override
    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws Exception {
        TextWaterMarkParam text = waterMarkParam.getText();
        // 字体
        Font font = new Font(text.getFontFamily(), 0, text.getFontSize());

        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        String content = text.getContent();
        int width = caculateTextWidth(metrics, content);
        int height = metrics.getHeight();

        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = destImage.createGraphics();

        // 设置文字边缘抗锯齿
//        graphics2D.setRenderingHint(Renderi
//       ngHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 设置水印文字透明度
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.5f));
        // TODO 颜色解析
        graphics2D.setColor(Color.GRAY);
        graphics2D.setFont(font);
        graphics2D.drawString(content, 0, metrics.getAscent());

        graphics2D.dispose();
        destImage.flush();

        return destImage;
    }

    private int caculateTextWidth(FontDesignMetrics metrics, String content) {
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }
}
