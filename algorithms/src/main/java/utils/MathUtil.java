package utils;

/**
 * 运算相关
 * @author zh
 * 2018年8月13日
 */
public class MathUtil {

	public static boolean isOperator(String str) {
		if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isPlus(String str) {
		return "+".equals(str);
	}
	public static boolean isMinus(String str) {
		return "-".equals(str);
	}
	public static boolean isMultiplication(String str) {
		return "*".equals(str);
	}
	public static boolean isDivision(String str) {
		return "/".equals(str);
	}
}
