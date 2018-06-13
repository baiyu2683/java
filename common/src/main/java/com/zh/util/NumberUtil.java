package com.zh.util;

import java.math.BigDecimal;

/**
 * 数字相关
 */
public class NumberUtil {
    /**
     * double科学技术法，转普通字符串
     * @param num
     * @return
     */
    public static String doubleToString(Double num) {
        if (num == null)
            throw new NullPointerException("num is null");
        BigDecimal bd = new BigDecimal(num);
        return bd.toString();
    }
}
