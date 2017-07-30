package com.zh;

import com.zh.chapter5.ProductInfo;

import java.io.IOException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-07-30.
 */
public class TestExchanger {
    public static void main(String[] args) throws IOException {
        Exchanger<ProductInfo> exchanger = new Exchanger<>();
        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ProductInfo productInfo = new ProductInfo("123");
                try {
                    exchanger.exchange(productInfo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setName("thread1");
        Thread thread2 = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    ProductInfo productInfo = exchanger.exchange(null);
                    System.out.println("2:" + productInfo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
        System.in.read();
    }
}
