package com.zh.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
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
            ProductInfo productInfo = new ProductInfo();
            productInfo.setInfo("消息...");
            return productInfo;
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
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
            return future.get();
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e;
        }
    }
}
