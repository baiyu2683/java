package com.zh;

import com.zh.chapter5.BoundedHashSet;
import com.zh.chapter5.ProductInfo;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-07-30.
 */
public class TestBoundedHashSet {

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<ProductInfo> set = new BoundedHashSet<>(5);
        for (int i = 0 ; i < 6 ; i++) {
            final int tmp = i;
            new Thread(() -> {
                try {
                    System.out.println(set.add(new ProductInfo()) + "-" + tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
