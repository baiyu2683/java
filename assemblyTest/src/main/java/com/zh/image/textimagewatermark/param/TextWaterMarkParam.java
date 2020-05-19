package com.zh.image.textimagewatermark.param;

import java.io.Serializable;

/**
 * 文字水印
 */
public class TextWaterMarkParam implements Serializable {

    /**
     * 文字内容
     */
    private String content;

    /**
     * 字体
     */
    private String fontFamily;

    /**
     * 字号
     */
    private Integer fontSize;

    /**
     * 文字颜色, red, rgb(255, 255, 0), rgba(255, 255, 0, 0.1), #fff
     */
    private String color;

    public TextWaterMarkParam() {
        fontFamily = "微软雅黑";
        fontSize = 12;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
