package com.zh.chapter1.chapter14;

import java.util.concurrent.TimeUnit;

/**
 * 记录对象从创建所经历的时间
 */
public class StopWatch {

    private long start = System.currentTimeMillis();

    public double elapseTime() {
        return (System.currentTimeMillis() - start) / 1000;
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(stopWatch.elapseTime());
    }
}
