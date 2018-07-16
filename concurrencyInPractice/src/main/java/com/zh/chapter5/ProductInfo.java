package com.zh.chapter5;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-07-30.
 */
public class ProductInfo {

    private String info;

    public ProductInfo(String info) {
        this.info = info;
    }

    public ProductInfo() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String toString() {
        return Thread.currentThread().getName() + " " + info;
    }
}
