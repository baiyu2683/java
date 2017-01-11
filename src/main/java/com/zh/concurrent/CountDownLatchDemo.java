package com.zh.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-01-11.
 */
public class CountDownLatchDemo {
    static final int SIZE = 100;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0; i < 10; i++)
            executorService.execute(new WaitingTask(latch));
        for(int i = 0; i < SIZE; i++)
            executorService.execute(new TaskProtion(latch));
        System.out.printf("Launched all tasks");
        TimeUnit.SECONDS.sleep(20);
        executorService.shutdown();
    }
}

class TaskProtion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;
    TaskProtion(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try {
            dowWork();
            latch.countDown();
        } catch (InterruptedException e) {

        }
    }
    public void dowWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + "completed");
    }
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;
    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try{
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }
    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }
}
