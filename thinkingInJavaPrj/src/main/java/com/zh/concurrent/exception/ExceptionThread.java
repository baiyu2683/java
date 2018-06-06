package com.zh.concurrent.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zh on 2016/12/24.
 */
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
    public static void main(String[] args) {
        //线程中抛出的异常时无法被其他线程捕获的，包括其父进程
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
