package com.zh.poi.util;

/**
 * poi中各种单位转换
 *
 * @author zh
 * 2018年8月10日
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
     * -1px - 1
     * @return
     */
    public static int pxString2Number(String px) {
        px = px.replace("px", "");
        return Math.abs(Integer.parseInt(px));
    }
}
