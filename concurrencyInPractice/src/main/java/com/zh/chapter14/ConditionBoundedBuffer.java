package com.zh.chapter14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zh on 2017-11-08.
 */
public class ConditionBoundedBuffer<T> {
    private final int BUFFER_SIZE = 16;
    protected final Lock lock = new ReentrantLock();
    //条件谓词 notFull(count < items.length)
    private final Condition notFull = lock.newCondition();
    //条件谓词 notEmpty (count > 0);
    private final Condition notEmpty = lock.newCondition();
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int tail, head, count;

    public void put(T x) throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            while (count == items.length)
                notFull.await();
            items[tail] = x;
            if(++tail == items.length)
                tail = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            while (count == 0)
                notEmpty.await();
            T x = items[head];
            if(++head == items.length)
                head = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
