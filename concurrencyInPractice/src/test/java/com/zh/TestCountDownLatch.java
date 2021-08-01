package com.zh;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;

public class TestCountDownLatch {
    // 同时阻塞多个线程，直到所有线程都调用了等待操作
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("开门: " + System.currentTimeMillis());
        }
    });
    public static class StartAllThread implements Runnable {
        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void startAll() throws InterruptedException {
        for (int i = 0 ; i < 3 ; i++) {
            new Thread(new StartAllThread()).start();
        }
        new CountDownLatch(1).await();
    }

    // 每个线程持有自己的开门锁和另外一个线程的开门锁
    // 当本线程执行完毕时，开启另外一个线程的执行
    public static class StartOneByOneThread implements Runnable {

        public CountDownLatch start;
        public CountDownLatch end;

        public StartOneByOneThread(CountDownLatch start, CountDownLatch end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                if (start != null) {
                    start.await();
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (end != null) {
                    end.countDown();
                }
            }
        }
    }

    @Test
    public void startOneByOne() throws InterruptedException {
        CountDownLatch oneThread = new CountDownLatch(1);
        CountDownLatch twoThread = new CountDownLatch(1);
        CountDownLatch threeThread = new CountDownLatch(1);
        new Thread(new StartOneByOneThread(null, twoThread)).start();
        new Thread(new StartOneByOneThread(twoThread, threeThread)).start();
        new Thread(new StartOneByOneThread(threeThread, null)).start();
        new CountDownLatch(1).await();
    }

    private static BlockingQueue<StartOneByOneThread2> taskQueue = new LinkedBlockingQueue<>();
    private static StartOneByOneThread2 END_TASK = new StartOneByOneThread2(null);
    public static class StartOneByOneThread2 implements Runnable {
        private StartOneByOneThread2 nextThread;
        public StartOneByOneThread2(StartOneByOneThread2 nextThread) {
            this.nextThread = nextThread;
        }
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                taskQueue.add(nextThread);
            }
        }
    }

    @Test
    public void startOneByOne2() throws InterruptedException {
        StartOneByOneThread2 t3 = new StartOneByOneThread2(END_TASK);
        StartOneByOneThread2 t2 = new StartOneByOneThread2(t3);
        StartOneByOneThread2 t1 = new StartOneByOneThread2(t2);
        taskQueue.put(t1);
        for (;;) {
            Runnable task = taskQueue.take();
            if (task == END_TASK) {
                break;
            }
            new Thread(task).start();
        }
    }

    private static Semaphore semaphore = new Semaphore(1);
    public static class StartOnlyOneThread implements Runnable {

        @Override
        public void run() {
            for (;;) {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void startOnlyOneThread() throws InterruptedException {
        for (int i = 0 ; i < 3 ; i++) {
            new Thread(new StartOnlyOneThread()).start();
        }
        new CountDownLatch(1).await();
    }
}
