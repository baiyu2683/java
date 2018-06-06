package com.zh.concurrent;

import com.zh.util.BasicGenerator;
import com.zh.util.Generator;

import java.util.List;
import java.util.concurrent.*;

/**
 * Exchanger用于两个线程之间的数据交换
 * Created by zh on 2017-01-15.
 */
public class ExchangerDemo {
    static int size = 10;
    static int delay = 5;
    public static void main(String[] args) throws Exception {
        if(args.length > 0) {
            size = new Integer(args[0]);
        }
        if(args.length > 1) {
            delay = new Integer(args[1]);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        List<Fat> producerList = new CopyOnWriteArrayList<>();
        List<Fat> consumerList = new CopyOnWriteArrayList<>();
        executorService.execute(new ExchangerConsumer<Fat>(exchanger, consumerList));
        executorService.execute(new ExchangerProducer<Fat>(exchanger, BasicGenerator.create(Fat.class), producerList));
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();
    }
}

class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    ExchangerProducer(Exchanger<List<T>> exchanger, Generator<T> generator, List<T> holder) {
        this.generator = generator;
        this.exchanger = exchanger;
        this.holder = holder;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                for(int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Producer...");
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {

        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                System.out.println("Consumer..." + holder.size());
                for (T x : holder) {
                    value = x;
                    holder.remove(x);
                }
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {

        }
        System.out.printf("Final value: " + value);
    }
}
