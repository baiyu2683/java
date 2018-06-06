package com.zh.chapter15;

/**
 * 利用cas实现的计数器
 * Created by zh on 2017-11-09.
 */
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get(); //对v的操作1
        }
        //cas操作无论是否成功都会返回旧值
        //这里v != cas操作，是指对v的操作1和操作2中间没有其他线程对v进行更新操作
        while (v != value.compareAndSwap(v, v + 1));//操作2，指!=号后面对v的cas操作
        return v + 1;
    }
}
