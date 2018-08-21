package com.zh.poi.util;

/**
 * poi中各种单位转换
 * 纸张宽高、页边距单位: 缇
 * dpi取96
 * @author zh
 */
public class UnitUtils {

    /**
     * 像素转点，忽略dpi
     * @param px
     * @return
     */
    public static double px2Point(double px) {
        return px * 72 / 96;
    }
 
    /**
     * html中长度字符串换算为数字
     * 10px -> 10
     * -1px -> 1
     * @return
     */
    public static int pxString2Number(String px) {
        px = px.replace("px", "");
        return Math.abs(Integer.parseInt(px));
    }
    
    /**
     * 像素转磅
     * @param px
     * @return
     */
    public static double px2Pong(double px) {
        return px * 3 / 4;
    }
    
    /**
     * 像素转缇
     * @param px
     * @return
     */
    public static double px2Twip(double px) {
        return 20 * px2Pong(px);
    }
    
    /**
     * 厘米转磅
     * Word中磅与厘米的换算关系,1磅约等于0.03527厘米,1厘米约等于28.35磅
     * @param cm
     * @return
     */
    public static double cm2Pong(double cm) {
        return 28.35 * cm;
    }
    
    /**
     * 厘米转缇
     * Word中磅与厘米的换算关系,1磅约等于0.03527厘米,1厘米约等于28.35磅
     * 磅和缇的换算关系,1缇约1/20磅
     * @param cm
     * @return
     */
    public static double cm2Twip(double cm) {
        return 20 * cm2Pong(cm);
    }
}
