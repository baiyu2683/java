package com.zh.poi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化类
 *
 * @author zh
 * 2018年8月21日
 */
public class DateTimeUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DEFAULT_PATTERN = DATE_PATTERN;
    
    
    private static final ThreadLocal<DateFormat> dateFormatLocal = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DEFAULT_PATTERN);
        }
    };
    
    /**
     * 日期格式化，默认格式
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null) return null;
        return dateFormatLocal.get().format(date);
    }
    
    /**
     * 日期格式化，指定格式
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (DEFAULT_PATTERN.equals(pattern)) {
            return format(date);
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
    
    /**
     * 时间戳格式化，默认格式
     * @param timeStamp
     * @return
     */
    public static String format(long timeStamp) {
        return format(new Date(timeStamp));
    }
    
    /**
     * 时间戳格式化，指定格式
     * @param timeStamp
     * @param pattern
     * @return
     */
    public static String format(long timeStamp, String pattern) {
        return format(new Date(timeStamp), pattern);
    }
}
