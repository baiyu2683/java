package com.zh.concurrent;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zh on 2017-01-09.
 */
public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        Car2 car = new Car2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff2(car));
        executorService.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}

class Car2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;
    //打蜡
    public void waxed() {
        lock.lock();
        System.out.println("waxed锁。。。");
        try {
            waxOn = true;
            condition.signalAll();
        } finally {
            lock.unlock();
            System.out.println("waxed没锁。。。");
        }
    }
    //抛光
    public void buffed() {
        lock.lock();
        try {
            waxOn = false;
            condition.signalAll();     //唤醒
        } finally {
            lock.unlock();
        }
    }
    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        System.out.println("waitforWaxing锁。。。");
        try {
            while (waxOn == false)
                condition.await();  //挂起
        } finally {
            lock.unlock();
            System.out.println("waitforWaxing没锁。。。");
        }
    }
    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == true)
                condition.await();
        } finally {
            lock.unlock();
        }
    }
}

class WaxOn2 implements Runnable {
    private Car2 car;
    public WaxOn2(Car2 car) {
        this.car = car;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
    }
}

class WaxOff2 implements Runnable {
    private Car2 car;
    public WaxOff2(Car2 car) {
        this.car = car;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();;
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.printf("Ending Wax Off task");
    }
}
