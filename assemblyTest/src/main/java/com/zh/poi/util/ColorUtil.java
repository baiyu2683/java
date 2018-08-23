package com.zh.poi.util;

import java.awt.Color;

/**
 * 各种格式颜色格式的互相转换
 *
 * @author zh
 * 2018年8月10日
 */
public class ColorUtil {
	/**
	 *
	 * @param bgr
	 * @return
	 */
	public static int bgr2rgb(int bgr) {
		Color c = new Color(bgr);
		int rgb = new Color(c.getBlue(), c.getGreen(), c.getRed()).getRGB();
		return rgb;
	}
	
	/**
	 * @param color
	 * @return
	 */
	public static String color2String(int color) {
		return color2String(new Color(color));
	}

	/**
	 * @param color
	 * @return
	 */
	public static String color2String(Color color) {
		return getRGBShort(color);
	}
	
	/**
	 * @param color
	 * @return
	 */
	public static String getRGBShort(Color color) {
		StringBuffer sb = new StringBuffer();
		if (color.getRed() < 16) {
			sb.append('0');
		}
		sb.append(Integer.toHexString(color.getRed()));
		
		if (color.getGreen() < 16) {
			sb.append('0');
		}
		sb.append(Integer.toHexString(color.getGreen()));
		
		if (color.getBlue() < 16) {
			sb.append('0');
		}
		sb.append(Integer.toHexString(color.getBlue()));
		return sb.toString();
	}
	
	/**
	 * @param bgr
	 */
	public static String getRGBShort(int bgr) {
		return getRGBShort(new Color(bgr));
	}

	/**
	 * @param color
	 * @return
	 */
	public static String color2RGBA(Color color) {
		StringBuffer sb = new StringBuffer();
		sb.append("rgba(");
		sb.append(color.getRed());
		
		sb.append(',');
		sb.append(color.getGreen());
		
		sb.append(',');
		sb.append(color.getBlue());
		
		sb.append(',');
		sb.append(color.getTransparency());
		sb.append(')');
		return sb.toString();
	}
	public static String color2RGBA(int color) {
		return color2RGBA(new Color(color));
	}
	
	/**
	 * @param color
	 * @return
	 */
	public static String color2RGB(Color color) {
		StringBuffer sb = new StringBuffer();
		sb.append("rgb(");
		sb.append(color.getRed());
		
		sb.append(',');
		sb.append(color.getGreen());
		
		sb.append(',');
		sb.append(color.getBlue());
		sb.append(')');
		return sb.toString();
	}
	public static String color2RGB(int color) {
		return color2RGB(new Color(color));
	}
	
    /**
     * rgb(255, 0, 0)或者rgba(255, 0, 0, 0.5)格式转Color对象, 忽略透明度<br/>
     * @param color
     * @return
     */
    public static Color rgbStr2Color(String color) {
        try {
            color = color.toLowerCase();
            String[] rgb = null;
            if (color.startsWith("rgb(")) {
                color = color.replace("rgb(", "")
                            .replace(")", "");
                rgb = color.split(", ");
            } else {
                color = color.replace("rgba(", "")
                        .replace(")", "");
                rgb = color.split(", ");
                if ("0".equals(rgb[3])) {
                    rgb[0] = rgb[1] = rgb[2] = "255";
                }
            }
            Color poiColor = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]),
                    Integer.parseInt(rgb[2]));
            return poiColor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
