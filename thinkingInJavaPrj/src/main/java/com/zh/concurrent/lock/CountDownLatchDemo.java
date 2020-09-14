package com.zh.concurrent.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class CountDownLatchDemo {

    private static Integer TASK_COUNT = 100;
    private static CountDownLatch startGate = new CountDownLatch(TASK_COUNT);
    private static CountDownLatch endGate = new CountDownLatch(TASK_COUNT);

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(TASK_COUNT, TASK_COUNT, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(TASK_COUNT));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0 ; i < TASK_COUNT ; i++) {
            executor.execute(new TestTask("task-" + i));
        }
        endGate.await();
        System.out.println("结束所有任务...");
        executor.shutdownNow();
    }

    private static class TestTask implements Runnable {

        private String name;

        private TestTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            startGate.countDown();
            try {
                startGate.await();
                System.out.println("执行任务: " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("结束任务: " + name);
                endGate.countDown();
            }
        }
    }
}
