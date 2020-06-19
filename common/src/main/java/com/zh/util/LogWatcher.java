package com.zh.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 记录当前线程日志信息, 格式: 线程id-事件-日志信息
 */
public class LogWatcher {

    private static final ThreadLocal<List<String>> logThreadLocal = new ThreadLocal<List<String>>() {
        protected List<String> initialValue() {
            return new ArrayList<String>();
        }
    };

    private static final ThreadLocal<SimpleDateFormat> formatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };

    public static void addLog(String log) {
        String thread = Thread.currentThread().getName();
        List<String> logs = logThreadLocal.get();
        logs.add(thread + "-" + formatThreadLocal.get().format(new Date()) + " : " + log);
    }

    public static void print() {
        List<String> logs = logThreadLocal.get();
        for (String log : logs) {
            System.out.println(log);
        }
        logThreadLocal.remove();
    }
}
