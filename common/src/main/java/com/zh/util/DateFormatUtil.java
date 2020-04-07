package com.zh.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        System.out.println(format((new Date()).getTime()));
        System.out.println(format(new Date()));
    }

    /**
     * 格式化时间戳
     * @param timestamp
     * @return
     */
    public static String format(long timestamp) {
        return dateFormatThreadLocal.get().format(timestamp);
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return format(date.getTime());
    }
}
