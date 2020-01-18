package com.zh.chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 限制集合数量的set
 * 通过在添加是获取许可，在移除时释放许可实现数量控制
 * Created by zh on 2017-07-30.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        boolean success = semaphore.tryAcquire(3, TimeUnit.SECONDS);
        if (!success) {
            return false;
        }
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            //加入失败时信号量释放
            if(!wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if(wasRemoved)
            semaphore.release();
        return wasRemoved;
    }

    public int availablePermits() {
        return semaphore.availablePermits();
    }
}
