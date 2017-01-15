package com.zh.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by zh on 2017-01-15.
 */
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<T>();
    private volatile boolean[] checkedOut;
    private Semaphore available;
    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        //size:许可个数，fire:为true表示保证所有线程在竞争条件下，以先进先出的形式获得许可，为false表示线程以任意顺序获得许可
        available = new Semaphore(size, true);
        for(int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }
    public void checkIn(T x) {
        if(releaseItem(x))
            available.release();
    }

    public synchronized T getItem() {
        for(int i = 0; i < size; i++) {
            if(!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }
    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if(index == -1)
            return false;
        if(checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
