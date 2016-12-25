package com.zh.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zh on 2016/12/25.
 */
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);
    public int getValue() { return i.get(); }
    private void evenIncrement() { i.addAndGet(2); }
    @Override
    public void run() {
        while(true) {
            evenIncrement();
        }
    }
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest alt = new AtomicIntegerTest();
        executorService.execute(alt);
        while (true) {
            int val = alt.getValue();
            System.out.println(val);
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
