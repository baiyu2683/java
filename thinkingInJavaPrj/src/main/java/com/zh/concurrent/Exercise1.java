package com.zh.concurrent;

/**
 * Created by zh on 2016/12/18.
 */
public class Exercise1 implements Runnable {
    public Exercise1() {
        //这里打印的是创建线程
        System.out.println(Thread.currentThread().getId()+"-启动");
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            //这里打印的才是创建好的Exercise1
            System.out.println(Thread.currentThread().getId() + "-张恒");
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getId() + "-终止");
    }
}
