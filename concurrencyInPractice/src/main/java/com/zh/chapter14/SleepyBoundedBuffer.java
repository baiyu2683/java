package com.zh.chapter14;

import java.util.concurrent.TimeUnit;

/**
 * 不断地获得锁，失败时休眠一段时间。这种方法在条件为真和程序执行判断时有一定延迟
 * Created by zh on 2017-10-29.
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if(!isFull()) {
                    doPut(v);
                    return;
                }
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if(!isEmpty()) {
                    return doTake();
                }
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
