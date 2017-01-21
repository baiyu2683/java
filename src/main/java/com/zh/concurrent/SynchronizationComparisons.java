package com.zh.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zh on 2017-01-21.
 */
public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synchronizedTest = new SynchronizedTest();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();
    static void test() {
        System.out.println("==============================");
        System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synchronizedTest.timedTest();
        lockTest.timedTest();
        atomicTest.timedTest();
        Accumulator.report(synchronizedTest, baseLine);
        Accumulator.report(lockTest, baseLine);
        Accumulator.report(atomicTest, baseLine);
        Accumulator.report(synchronizedTest, lockTest);
        Accumulator.report(synchronizedTest, atomicTest);
        Accumulator.report(lockTest, atomicTest);
    }
    public static void main(String[] args) {
        int iterations = 5;
        if(args.length > 0)
            iterations = new Integer(args[0]);
        System.out.printf("Warmup");
        baseLine.timedTest();
        for(int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.executorService.shutdown();
    }
}
abstract class Accumulator {
    public static long cycles = 50000L;
    private static final int N = 4;
    public static ExecutorService executorService = Executors.newFixedThreadPool(N * 2);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(N * 2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE];
    static {
        Random random = new Random(47);
        for(int i = 0; i < SIZE; i++) {
            preLoaded[i] = random.nextInt();
        }
    }
    public abstract void accumulate();
    public abstract long read();
    private class Modifier implements Runnable {
        public void run() {
            for(long i = 0; i < cycles; i++)
                accumulate();
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class Reader implements Runnable {
        public void run() {
            for(long i = 0; i < cycles; i++) {
                value = read();
            }
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void timedTest() {
        long start = System.nanoTime();
        for(int i = 0; i < N; i++) {
            executorService.execute(new Modifier());
            executorService.execute(new Reader());
        }
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime();
        System.out.printf("%-13s: %13d\n", id, duration);
    }
    public static void report(Accumulator accumulator1, Accumulator accumulator2) {
        System.out.printf("%-22s: %.2f\n", accumulator1.id + "/" + accumulator2.id, (double)accumulator1.duration/(double)accumulator2.duration);
    }
}
class BaseLine extends Accumulator {
    { id = "BaseLine"; }
    public void accumulate() {
        value += preLoaded[index >= SIZE - 1? 0: index++];
        if(index >= SIZE) index = 0;
    }
    public long read() {
        return value;
    }
}
class SynchronizedTest extends Accumulator {
    { id = "synchronized"; }
    public synchronized void accumulate() {
        value += preLoaded[index++];
        if(index >= SIZE) index = 0;
    }
    public synchronized long read() {
        return value;
    }
}
class LockTest extends Accumulator {
    { id = "Lock"; }
    private Lock lock = new ReentrantLock();
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if(index >= SIZE) index = 0;
        } finally {
            lock.unlock();
        }
    }
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}
class AtomicTest extends Accumulator {
    { id = "Atomic"; }
    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);
    public void accumulate() {
        int i = index.getAndIncrement();
        if(++i >= SIZE) {
            index.set(0);
             i = 0;
        }
        value.getAndAdd(preLoaded[i]);
    }
    public long read() {
        return value.get();
    }
}
