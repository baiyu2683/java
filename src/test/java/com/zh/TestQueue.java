package com.zh;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-08-01.
 */
public class TestQueue {

    @Test
    public void testDelayQueue() throws InterruptedException {
        DelayQueue<MyDelayed> delayeds = new DelayQueue<>();
        for(int i = 0; i < 10; i++) {
            delayeds.add(new MyDelayed(Math.round(Math.random() * 10)));
        }
        long start = System.currentTimeMillis();
        while (delayeds.size() > 0) {
            System.out.println(delayeds.take());
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    static class MyDelayed implements Delayed {

        //单位s
        private long delay;
        private long nextTime;

        public MyDelayed(long delay) {
            this.delay = delay;
            nextTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delay, TimeUnit.SECONDS);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(nextTime - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return nextTime > ((MyDelayed)o).nextTime? 1 : (nextTime < ((MyDelayed)o).nextTime ? -1: 0);
        }

        public String toString() {
            return Thread.currentThread().getName() + "-delay:" + delay;
        }
    }
}
