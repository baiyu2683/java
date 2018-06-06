package com.zh.concurrent.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 为线程执行设置默认的未捕获异常处理器
 * Created by zh on 2016/12/25.
 */
public class SettingDefaultHandler {
    public static void main(String[] args) {
        //--------在其他线程为设置异常捕获处理时，调用默认的-----
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        //--------------
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
    }
}
