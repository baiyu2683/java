package com.zh.chapter8;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-10-08.
 */
public class ExecutorSetter {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        if(executorService instanceof ThreadPoolExecutor) {
           ThreadPoolExecutor exec = ((ThreadPoolExecutor) executorService);
           exec.setCorePoolSize(0);
           exec.setMaximumPoolSize(1); //只有一个线程
        } else
            throw new AssertionError("Oops, bad assumption");
        for(int i = 0; i < 10; i++)
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println(1);
            });
    }
}
