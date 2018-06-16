package com.zh.log4j;

import com.zh.constant.Consts;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * log4j日志记录
 */
public class Log4j {

    private static final Logger logger = Logger.getLogger(Log4j.class);

    // 配置目录
    private static final String PATH_CONF = "/conf";
    // 日志目录
    private static final String PATH_LOG = "/log";
    // 日志配置文件路径
    private static final String PATH_FILE_LOG4j = PATH_CONF + "/log4j.properties";
    // file这个路径和log4j.properties中的名字对应，因为需要动态设置，所以放在这里
    private static final String CONFIG_LOG4J_APPENDER_FILE = "log4j.appender.file.File";
    // 日志文件后缀
    private static final String SUFFIX_FILE_LOG = ".log";
    // jar
    private static final String SUFFIX_JAR = "jar";
    // lib
    private static final String NAME_LIB = "lib";
    // class文件目录名
    private static final String NAME_CLASSES = "classes";

    private static final AtomicInteger init_count = new AtomicInteger(0);
    private static final ReentrantLock lock = new ReentrantLock();
    /**
     * 日志初始化
     * @param clazz
     */
    public static void init(Class<?> clazz) {
        int count = init_count.getAndIncrement();
        try {
            lock.lockInterruptibly();
            lock.tryLock();
            if (count != 0) return;
            initLog4jConfig(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        } finally {
            lock.unlock();
        }
    }

    private static void initLog4jConfig(Class<?> clazz) {
        // 加载配置文件
        InputStream is;
        String path = Log4j.class.getResource("").getPath();
        String logPath = PATH_LOG;
        // 打包为jar
        if (path.contains(SUFFIX_JAR)) {
            try {
                path = path.substring(0, path.lastIndexOf(NAME_LIB));
                path = path.substring(0, path.lastIndexOf(Consts.PATH_SEPARATOR));
                URI uri = new URI(path);
                is = new FileInputStream(new File(uri.getPath() + PATH_FILE_LOG4j));
                logPath = uri.getPath() + logPath;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("获取日志配置失败!");
            }
        } else {
            // 在IDE中
            is = Log4j.class.getResourceAsStream(PATH_FILE_LOG4j);
            logPath = path.substring(0, path.lastIndexOf(NAME_CLASSES));
            logPath = logPath + PATH_LOG;
        }
        File logDir = new File(logPath);
        if (!logDir.exists())
            logDir.mkdirs();

        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(is));
            properties.setProperty(CONFIG_LOG4J_APPENDER_FILE, logDir.getPath() + File.separator + clazz.getSimpleName() + SUFFIX_FILE_LOG);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化日志配置失败！");
        }
    }

    public static void main(String[] args) {
        Log4j.init(Log4j.class);
        info("测试info");
        debug("测试debug");
    }

    // 字符拼接
    private static ThreadLocal<StringBuilder> stringBuilderThreadLocal = new ThreadLocal<StringBuilder>() {
        protected StringBuilder initialValue() {
            return new StringBuilder(0);
        }
    };

    public static void info(String...infos) {
        if (logger.isInfoEnabled())
            logger.info(appendInfo(infos));
    }

    public static void debug(String...debugInfos) {
        if (logger.isDebugEnabled())
            logger.debug(appendInfo(debugInfos));
    }

    public static void warn(Throwable throwable, String...warnInfos) {
        if (Level.WARN.isGreaterOrEqual(logger.getLevel()))
            logger.warn(appendInfo(warnInfos), throwable);
    }

    public static void warn(String...warnInfos) {
        if (Level.WARN.isGreaterOrEqual(logger.getLevel()))
            logger.warn(appendInfo(warnInfos));
    }

    public static void error(String...errorInfos) {
        if (Level.ERROR.isGreaterOrEqual(logger.getLevel()))
            logger.error(appendInfo(errorInfos));
    }

    public static void error(Throwable throwable, String...errorInfos) {
        if (Level.ERROR.isGreaterOrEqual(logger.getLevel()))
            logger.error(appendInfo(errorInfos), throwable);
    }

    private static StringBuilder appendInfo(String[] infos) {
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
        return stringBuilder;
    }
}
