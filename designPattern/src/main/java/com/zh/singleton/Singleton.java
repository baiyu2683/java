package com.zh.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例:
 * 1，私有静态实例域
 * 2，私有构造函数
 * 3，获取实例方法，两次判断，防止多线程下多个实例
 */
public class Singleton {

    private static Singleton instance;

    private static AtomicInteger counter = new AtomicInteger(0);

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    counter.addAndGet(1);
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static int counter() {
        return counter.get();
    }
}
