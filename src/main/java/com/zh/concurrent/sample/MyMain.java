package com.zh.concurrent.sample;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by zhangheng on 2016/10/17.
 */
public class MyMain {

    public static void main(String[] args){
//        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<Runnable>();
//        //只有一个线程
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 3000, TimeUnit.SECONDS,blockingQueue);
        for(int i = 0; i < 50; i++){
            Producer producer = new Producer(i);
            threadPoolExecutor.execute(producer);
        }
        //放在队列最后，最后执行
        Consumer consumer = new Consumer();
        threadPoolExecutor.execute(consumer);
        threadPoolExecutor.shutdown();

//        ExecutorService executor = Executors.newCachedThreadPool();
//        for(int i = 0; i < 50; i++){
//            Producer producer = new Producer(i);
//            executor.execute(producer);
//        }
//        //放在队列最后，最后执行
//        Consumer consumer1 = new Consumer();
//        executor.execute(consumer1);
//        executor.shutdown();
    }
}
