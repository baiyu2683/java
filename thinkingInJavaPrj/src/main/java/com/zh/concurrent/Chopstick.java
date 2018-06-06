package com.zh.concurrent;

/**
 * Created by zh on 2017-01-11.
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
