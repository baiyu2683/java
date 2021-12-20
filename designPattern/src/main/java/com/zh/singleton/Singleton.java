package com.zh.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单例:
 * 1，私有静态实例域
 * 2，私有构造函数
 * 3，获取实例方法，两次判断，防止多线程下多个实例
 */
public class Singleton {

    /**
     * volatile可以禁止指令重排序，防止获得未完全初始化好的对象
     * instance = new Singleton()不是原子的，分为三步
     * 1. 申请空间
     * 2. 初始化，此时instance == null
     * 3. 将引用指向申请的空间  此时instance ！= null
     * 其中第二步和第三步是可以重排序的，对最终对象生成没有影响。会变成下面着这样
     * 1. 申请空间
     * 2. 将引用指向申请的空间  此时instance ！= null
     * 3. 初始化，此时instance ！= null
     * 如果发生重排序，其他线程可能会得到还未初始化完成的对象。
     */
    private volatile static Singleton instance;
//    private static Singleton instance;

    /**
     * 测试指令重排序
     */
    private Integer volatileCheck = 1;

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

    public Integer getVolatileCheck() {
        return volatileCheck;
    }

    public static int counter() {
        return counter.get();
    }
}
