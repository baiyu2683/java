package com.zh.chapter4.examples;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 简易线程池
 */
public class ThreadPool {

    private LinkedList<Job> jobs = new LinkedList<>();
    private LinkedList<Worker> workers = new LinkedList<>();

    private int max_workers_num = Runtime.getRuntime().availableProcessors();

    private AtomicLong num = new AtomicLong(0);

    public ThreadPool(int poolSize) {
        max_workers_num = poolSize;
        initWorkers();
    }

    public void addJob(Job job) {
        synchronized (jobs) {
            jobs.add(job);
            jobs.notify();
        }
    }

    private void initWorkers() {
        for (int i = 0 ; i < max_workers_num ; i++) {
            Worker worker = new Worker();
            workers.add(new Worker());
            Thread thread = new Thread(worker, "ThreadPool-" + num.getAndIncrement());
            thread.start();
        }
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                        System.out.println("thread: " + Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10);
        for (int i = 0 ; i < 11 ; i++) {
            threadPool.addJob(new Job());
        }
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
