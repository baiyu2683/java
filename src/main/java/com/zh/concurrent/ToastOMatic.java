package com.zh.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-01-10.
 */
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue1 = new ToastQueue(),dryQueue2 = new ToastQueue(),
                butteredQueue = new ToastQueue(),jammerQueue = new ToastQueue(),
                mergeQueue = new ToastQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(dryQueue1));
        executorService.execute(new Butterer(dryQueue1, butteredQueue));
        executorService.execute(new Toaster(dryQueue2));
        executorService.execute(new Jammer(dryQueue2,jammerQueue));
        executorService.execute(new Merge(butteredQueue, jammerQueue, mergeQueue));
        executorService.execute(new Eater(mergeQueue));
        TimeUnit.SECONDS.sleep(1000);
        executorService.shutdownNow();
    }
}

class Toast {
    public enum Status{ DRY, BUTTERED, JAMMED, MERGE }
    private Status status = Status.DRY;
    private final int id;
    public Toast(int idn) {
        id = idn;
    }
    public void butter() {
        status = Status.BUTTERED;
    }
    public void jam() {
        status = Status.JAMMED;
    }
    public void merge() {
        status = Status.MERGE;
    }
    public Status getStatus() {
        return status;
    }
    public int getId() {
        return id;
    }
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);
    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                if(toastQueue.size() < 100) {
                    Toast t = new Toast(count++);
                    System.out.println(t);
                    toastQueue.put(t);
                }
            }
        } catch (InterruptedException e) {
            System.out.printf("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements Runnable {
    private ToastQueue dryQueue, fineshedQueue;
    public Butterer(ToastQueue dry, ToastQueue fineshed) {
        dryQueue =dry;
        fineshedQueue = fineshed;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(2);
                Toast t = dryQueue.take();  //阻塞的
                t.butter();
                System.out.println(t);
                fineshedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;
    public Jammer(ToastQueue buttered, ToastQueue finished) {
        butteredQueue = buttered;
        finishedQueue = finished;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if(finishedQueue.size() < 50) {
                    Toast t = butteredQueue.take();
                    t.jam();
                    System.out.println(t);
                    finishedQueue.put(t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

class Merge implements Runnable {
    private ToastQueue butteredQueue, jammeredQueue, finishedQueue;
    public Merge(ToastQueue buttered, ToastQueue jammer, ToastQueue finished) {
        butteredQueue = buttered;
        jammeredQueue = jammer;
        finishedQueue = finished;
    }
    public void run() {
        Random random = new Random(47);
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                Toast t1 = jammeredQueue.take();
                System.out.println(t + ";" + t1);
                Toast t11 = new Toast(random.nextInt(1000));
                t11.merge();
                finishedQueue.put(t11);
            }
        } catch (InterruptedException e) {
            System.out.println("Merge interrupted");
        }
        System.out.println("Merge off");
    }
}

class Eater implements Runnable {
    private ToastQueue finishedQueue;
    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if(t.getStatus() != Toast.Status.MERGE) {
                    System.out.printf(">>>> Error: " + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.printf("Eatter off");
    }
}
