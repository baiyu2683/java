package com.zh;


import com.zh.log4j.Log4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JavaEnvTest {

    public static void main(String[] args) throws InterruptedException {
        int core_num = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(core_num,
                core_num * 2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50));
        for (int i = 0; i < 50; i++) {
            tpe.execute(() -> {
                Log4j.init(JavaEnvTest.class);
            });
        }
        TimeUnit.SECONDS.sleep(30);
        Log4j.info("初始化结束了...");
    }
}
