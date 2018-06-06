package com.zh.chapter14;

/**
 * Created by zh on 2017-11-08.
 */
public class ThreadGate {
    private boolean isOpen; //条件谓词
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }
    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
