package com.zh;

import java.awt.Color;

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
//		String R = Integer.toHexString(color.getRed());
//		R = R.length() < 2 ? ('0' + R) : R;
//		sb.append(R);
//		String G = Integer.toHexString(color.getGreen());
//		G = G.length() < 2 ? ('0' + G) : G;
//		sb.append(G);
//		String B = Integer.toHexString(color.getBlue());
//		B = B.length() < 2 ? ('0' + B) : B;
//		sb.append(B);
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
}
