package com.zh.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 这个为啥get()回来会延迟呢?
 * 看start执行之后，任务也很快返回了，是不是future的状态没有及时改变?
 * MDZZ, 在ProductInfo构造函数里sleep了5s。。。
 * Created by zh on 2017-07-30.
 */
public class Preloader {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Preloader preloader = new Preloader();
        preloader.start();
        System.out.println(preloader.get());
    }

    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            System.out.println("task start execute: " + System.currentTimeMillis());
            ProductInfo productInfo = new ProductInfo();
            productInfo.setInfo("消息...");
            return productInfo;
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        System.out.println("start task: " + System.currentTimeMillis());
        thread.start();
    }

    public static RuntimeException launderThrowable(Throwable t) {
        if(t instanceof RuntimeException)
            return (RuntimeException) t;
        else if(t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    public ProductInfo get() throws InterruptedException, ExecutionException {
        try {
            ProductInfo pi = future.get();
            System.out.println("finished: " + System.currentTimeMillis());
            return pi;
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e;
        }
    }
}
