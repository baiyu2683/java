package com.zh.concurrent.myqueue;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 实现优先级队列，根据下次触发时间判定
 * Created by zh on 2017-06-29.
 */
public class DelayedQueueTest {
    public static void main(String[] args) {
        Random random = new Random(47);
        DelayQueue<MyDelayedQueue> delayedQueues = new DelayQueue<>();
        for(int i = 0; i < 10; i++) {
            int nextInt = random.nextInt(30);
            delayedQueues.put(new MyDelayedQueue(nextInt));
        }
        do {
            MyDelayedQueue myDelayedQueue = null;
            try {
                myDelayedQueue = delayedQueues.take();
                System.out.println(myDelayedQueue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (delayedQueues.size() > 0);
    }
}
class MyDelayedQueue implements Delayed {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 单位：s
    private long nextTime;
    private static int counter = 0;
    private int id = counter++;
    private long now = System.nanoTime();

    MyDelayedQueue(long delay) {
        //通过delay计算下次触发时间
        nextTime = now + TimeUnit.NANOSECONDS.convert(delay, TimeUnit.SECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(nextTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * 根据nextTime升序排序
     */
    @Override
    public int compareTo(Delayed o) {
        if (o == null) return 1;
        MyDelayedQueue myDelayedQueue = (MyDelayedQueue)o;
        if(nextTime < myDelayedQueue.nextTime) return -1;
        if(nextTime > myDelayedQueue.nextTime) return 1;
        return 0;
    }

    public String toString() {
        return "[" + id + "," + nextTime + "]";
    }
}
