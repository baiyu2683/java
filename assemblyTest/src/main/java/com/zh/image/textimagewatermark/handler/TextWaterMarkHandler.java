package com.zh.image.textimagewatermark.handler;

import com.zh.image.textimagewatermark.param.TextWaterMarkParam;
import com.zh.image.textimagewatermark.param.WaterMarkParam;
import org.apache.commons.lang3.StringUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TextWaterMarkHandler implements WaterMarkHandler {

    private static String DEFAULT_FONT_FAMILY = "微软雅黑";
    private static Font DEFAULT_FONT = new Font(DEFAULT_FONT_FAMILY, Font.PLAIN, 30);
    private static String[] NOT_SUPPORT_CHINESE_FONT_FAMILY = new String[]{"Arial", "Courier New", "Georgia", "Verdana"};

    @Override
    public BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws Exception {
        TextWaterMarkParam text = waterMarkParam.getText();

        List<TextContentHolder> textContentHolders = fontHandler(text.getContent(), text.getFontFamily(), text.getFontSize());

        int designAscent = 0;
        int designDescent = 0;
        int width = 0;
        int height = 0;
        for(TextContentHolder textContentHolder : textContentHolders) {
            caculateTextSize(textContentHolder);
            width += textContentHolder.width;
            if (height < textContentHolder.height) {
                height = textContentHolder.height;
                designAscent = textContentHolder.getFontMetrics().getAscent();
                designDescent = textContentHolder.getFontMetrics().getDescent();
            }
        }

        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D = destImage.createGraphics();

        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
        graphics2D.setColor(Color.BLACK);
        // 设置水印文字透明度
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.5f));
        int x = 0;
        int currentFontHeight = 0;
        for (TextContentHolder textContentHolder : textContentHolders) {
            graphics2D.setFont(textContentHolder.getFont());
            FontDesignMetrics fontDesignMetrics = textContentHolder.getFontMetrics();
            System.out.println(textContentHolder.getContent() + " " + fontDesignMetrics.getAscent() + " " +fontDesignMetrics.getDescent() + " " + fontDesignMetrics.getHeight());
            currentFontHeight = fontDesignMetrics.getHeight();
            int y = designAscent;
            if (currentFontHeight < height) {
                y = height - designDescent;
            }
            graphics2D.drawString(textContentHolder.getContent(), x, y);
            x += textContentHolder.width;
        }

        graphics2D.dispose();
        destImage.flush();

        return destImage;
    }

    /**
     * 字体可能不支持中文，分别处理，中文使用微软雅黑，其他使用设置的字体
     * @param content
     * @param fontFamily
     * @return
     */
    private List<TextContentHolder> fontHandler(String content, String fontFamily, int fontSize) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        List<TextContentHolder> fontHolders = new ArrayList<>();
        Font font = new Font(fontFamily, Font.PLAIN, fontSize);

        if (!notSupportChinese(fontFamily)) {
            fontHolders.add(new TextContentHolder(content, font, FontDesignMetrics.getMetrics(font)));
            return fontHolders;
        }

        char[] charArray = content.toCharArray();
        StringBuilder cacheSubContent = new StringBuilder();
        boolean first = true;
        boolean preChinese = false;
        for(char c : charArray) {

            if (first) {
                if (c >= 19968 && c <= 171941) {
                    preChinese = true;
                } else {
                    preChinese = false;
                }
                first = false;
            }

            /// 汉字范围 \u4e00-\u9fa5
            if (c >= 19968 && c <= 171941) {
                if (preChinese) {
                    cacheSubContent.append(c);
                } else {
                    fontHolders.add(new TextContentHolder(cacheSubContent.toString(), font, FontDesignMetrics.getMetrics(font)));
                    cacheSubContent.setLength(0);
                    cacheSubContent.append(c);
                }
                preChinese = true;
            } else {
                if (!preChinese) {
                    cacheSubContent.append(c);
                } else {
                    fontHolders.add(new TextContentHolder(cacheSubContent.toString(), DEFAULT_FONT, FontDesignMetrics.getMetrics(DEFAULT_FONT)));
                    cacheSubContent.setLength(0);
                    cacheSubContent.append(c);
                }
                preChinese = false;
            }
        }

        if (cacheSubContent.length() > 0) {
            if (preChinese) {
                fontHolders.add(new TextContentHolder(cacheSubContent.toString(), DEFAULT_FONT, FontDesignMetrics.getMetrics(DEFAULT_FONT)));
            } else {
                fontHolders.add(new TextContentHolder(cacheSubContent.toString(), font, FontDesignMetrics.getMetrics(font)));
            }
            cacheSubContent.setLength(0);
        }
        return fontHolders;
    }

    /**
     * 已知不支持中文字体
     * @param fontFamily
     * @return
     */
    private boolean notSupportChinese(String fontFamily) {
        if (StringUtils.isBlank(fontFamily)) {
            return true;
        }
        for (String f : NOT_SUPPORT_CHINESE_FONT_FAMILY) {
            if (f.equalsIgnoreCase(fontFamily)) {
                return true;
            }
        }
        return false;
    }

    private void caculateTextSize(TextContentHolder textContentHolder) {
        FontDesignMetrics fontDesignMetrics = textContentHolder.getFontMetrics();
        textContentHolder.width += fontDesignMetrics.stringWidth(textContentHolder.content);
        textContentHolder.height = fontDesignMetrics.getHeight();
    }

    /**
     * 字体相关
     */
    class TextContentHolder {

        private String content;

        private Font font;

        private FontDesignMetrics fontMetrics;

        private int width;

        private int height;

        public TextContentHolder(String content, Font font, FontDesignMetrics fontMetrics) {
            this.content = content;
            this.font = font;
            this.fontMetrics = fontMetrics;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public FontDesignMetrics getFontMetrics() {
            return fontMetrics;
        }

        public void setFontMetrics(FontDesignMetrics fontMetrics) {
            this.fontMetrics = fontMetrics;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
