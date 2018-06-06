package com.zh.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zh on 2017-01-12.
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> delayedTasks = new DelayQueue<>();
        for(int i = 0; i < 20; i++)
            delayedTasks.put(new DelayedTask(random.nextInt(5000)));
        delayedTasks.add(new DelayedTask.EndSentinel(5000, executorService));
        executorService.execute(new DelayedTaskConsumer(delayedTasks));
    }
}
class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<>();
    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if(trigger < that.trigger) return -1;
        if(trigger > that.trigger) return 1;
        return 0;
    }

    public void run() {
        System.out.println(this + " ");
    }

    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }
    public String summary() {
        return "(" + id + ":" + delta + ")";
    }
    public static class EndSentinel extends DelayedTask {
        private ExecutorService executorService;
        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            executorService = e;
        }
        public void run() {
            for(DelayedTask delayedTask : sequence) {
                System.out.println(delayedTask.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            executorService.shutdownNow();
        }
    }
}
class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> delayedTasks;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> delayedTasks) {
        this.delayedTasks = delayedTasks;
    }
    public void run() {
        try {
            while (!Thread.interrupted())
                delayedTasks.take().run();
        } catch (InterruptedException e) {

        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}
