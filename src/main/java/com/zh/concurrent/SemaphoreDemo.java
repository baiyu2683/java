package com.zh.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-01-15.
 */
public class SemaphoreDemo {
    final static int SIZE = 25;
    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < SIZE; i++) {
            executorService.execute(new CheckoutTask<Fat>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<Fat>();
        for(int i = 0; i < SIZE; i++) {
            Fat f  = pool.checkOut();
            System.out.println(i + ": main() thread checked out ");
            f.operation();
            list.add(f);
        }
        Future<?> blocked = executorService.submit(() -> {
            try {
                pool.checkOut();
            } catch (InterruptedException e) {
                System.out.println("checkOut() Interrupted");
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in " + list);
        for(Fat fat : list) {
            pool.checkIn(fat);
        }
        for(Fat fat : list) {
            pool.checkIn(fat);
        }
        executorService.shutdown();
    }
}

class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;
    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + "checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {

        }
    }
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}
