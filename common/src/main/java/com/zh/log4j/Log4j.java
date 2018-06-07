package com.zh.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * log4j日志记录
 */
public class Log4j {

    private static final Logger logger = Logger.getLogger(Log4j.class);

    static {
        Properties properties = new Properties();
        try {
            InputStream is = Log4j.class.getResourceAsStream("/conf/log4j.properties");
            properties.load(new InputStreamReader(is));
        } catch (IOException e) {
            System.out.println("启动失败");
            e.printStackTrace();
            throw new RuntimeException("日志初始化失败!");
        }
        // TODO 需要修正文件输出路径
        // ..
        PropertyConfigurator.configure(properties);
    }
    // 字符拼接
    private static ThreadLocal<StringBuilder> stringBuilderThreadLocal = new ThreadLocal<StringBuilder>() {
        protected StringBuilder initialValue() {
            return new StringBuilder(0);
        }
    };

    public static void info(String...infos) {
        StringBuilder stringBuilder = stringBuilderThreadLocal.get();
        stringBuilder.setLength(0);
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stes) {
            String clazzName = ste.getClassName();
            if (clazzName.startsWith("com.zh") && !clazzName.endsWith("Log4j")) {
                stringBuilder.append(" [").append(ste.getFileName()).append(":").append(ste.getLineNumber()).append("]");
                break;
            }
        }
        for (String info : infos) {
            stringBuilder.append(" [").append(info).append("]");
        }
        logger.info(stringBuilder.toString());
    }

    public static void debug(String...debug) {
        System.out.println(debug);
    }

    public static void error(String error, Throwable throwable) {
        System.out.println(error);
        throwable.getCause().printStackTrace();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        stackTraceElements[0].toString();
    }
}
