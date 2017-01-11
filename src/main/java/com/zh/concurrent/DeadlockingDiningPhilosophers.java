package com.zh.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-01-11.
 */
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 0;
        if(args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if(args.length > 1){
            size = Integer.parseInt(args[1]);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks1 = new Chopstick[size];
        for(int i = 0; i < size; i++)
            chopsticks1[i] = new Chopstick();
        for(int i = 0; i < size; i++){
            executorService.execute(new Philosopher(chopsticks1[i], chopsticks1[(i+1)%size], i, ponder));
        }
        if(args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        executorService.shutdownNow();
    }
}
