package com.zh.poi.constant;

/**
 * 需要处理的文本标签
 * 
 * 各种css样式或者样式标签定义了一个唯一键
 * 
 * @author zh
 * 2018年8月10日
 */
public interface HtmlTagConstants {

    // 需要处理的标签
    String TAG_DIV = "div";
    String TAG_P = "p";
    String TAG_IMG = "img";
    String TAG_FONT = "font";
    String TAG_U = "u";
    String TAG_I = "i";
    String TAG_B = "b";
    String TAG_STRIKE = "strike";
    String TAG_BR = "br";
    String TAG_SPAN = "span";
    
    // css属性名+属性值
    String STYLE_FONT_FAMILY = "font-family";
    String STYLE_FONT_SIZE = "font-size";
    String STYLE_TEXT_ALIGN = "text-align";
    String STYLE_FONT_WEIGHT = "font-weight";
    String STYLE_FONT_STYLE = "font-style";
    String STYLE_TEXT_DECORATION = "text-decoration";
    String STYLE_BACKGROUND_COLOR = "background-color";
    String STYLE_COLOR = "color";
    
    // font标签属性名+属性值
    String FONT_PROP_ALIGN = "align";
    String FONT_PROP_FACE = "face";
    String FONT_PROP_SIZE = "size";
    
    // i标签名+值
    String FONT_ITALIC = "font-italic";
    // b标签
    String FONT_BLOD = "font-bold";
    // u标签
    String FONT_UNDERLINE = "font-underline";
    // strike标签
    String FONT_STRIKE = "font-strike";
    
}
