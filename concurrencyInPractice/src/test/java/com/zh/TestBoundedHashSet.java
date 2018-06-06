package com.zh;

import com.zh.chapter5.BoundedHashSet;
import com.zh.chapter5.ProductInfo;

/**
 * Created by zh on 2017-07-30.
 */
public class TestBoundedHashSet {

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<ProductInfo> set = new BoundedHashSet<>(10);
        for (int i = 0; i < 9; i++) {
            System.out.println(set.add(new ProductInfo()) + "," + i);
        }
        System.out.println("wanle...");
    }
}
