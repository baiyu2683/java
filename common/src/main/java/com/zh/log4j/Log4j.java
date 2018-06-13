package com.zh.log4j;

import jdk.nashorn.internal.runtime.PropertyMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * log4j日志记录
 */
public class Log4j {

    private static final Logger logger = Logger.getLogger(Log4j.class);

    // jar部署目录名字必须为lib
    private static final String PATH_CONF = "/conf";
    private static final String PATH_LOG = "/log";
    private static final String PATH_LOG4j = "/log4j.properties";
    private static final String PATH_FILE_LOG4j = PATH_CONF + PATH_LOG4j;

    static {
        // 加载配置文件
        InputStream is = null;
        String path = Log4j.class.getResource("").getPath();
        String logPath = PATH_LOG;
        if (path.contains("jar")) {
            try {
                path = path.substring(0, path.lastIndexOf("lib"));
                path = path.substring(0, path.lastIndexOf("/"));
                URI uri = new URI(path);
                is = new FileInputStream(new File(uri.getPath() + PATH_FILE_LOG4j));

                logPath = uri.getPath() + logPath;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("获取日志配置失败!");
            }
        } else {
            is = Log4j.class.getResourceAsStream(PATH_FILE_LOG4j);

            logPath = path.substring(0, path.lastIndexOf("classes"));
            logPath = logPath + PATH_LOG;
        }
        File logDir = new File(logPath);
        if (!logDir.exists())
            logDir.mkdirs();

        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(is));
            System.out.println(new URI(logPath).getPath() + "/log.log");
            properties.setProperty("log4j.appender.file.File", logDir.getPath() + "/log.log");
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化日志配置失败！");
        }

    }

    public static void main(String[] args) {
        info("123");
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
