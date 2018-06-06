package com.zh.concurrent;

/**
 * Created by zh on 2016/12/25.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public synchronized static int nextSerialNumber() {
        return serialNumber++;  //这里不是原子的，也就不是线程安全的，需要用synchronized修饰方法
    }
}
