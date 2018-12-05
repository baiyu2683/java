package com.zh.util;

/**
 * 字符串相关
 */
public class StringUtil {

    /**
     * 是否包含中文
     * @param source
     * @return
     */
    public static boolean containsChinese(String source) {
        return RegexUtil.contains(source,"[\u4e00-\u9fa5]");
    }
}
