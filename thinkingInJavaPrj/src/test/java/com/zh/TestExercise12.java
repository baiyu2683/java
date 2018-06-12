package com.zh;

import com.zh.concurrent.Exercise1;
import com.zh.concurrent.Exercise2Fibonacci;
import com.zh.concurrent.LiftOff;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2016/12/18.
 */
public class TestExercise12 {

    @Test
    public void test1() throws IOException {
        for(int i = 0; i < 10; i++) {
            new Thread(new Exercise1()).start();
        }
        System.in.read();
    }

    @Test
    public void test2() throws IOException {
        Random r = new Random(47);
        for(int i = 0; i < 20; i++) {
            new Thread(new Exercise2Fibonacci(r.nextInt(100))).start();
        }
        System.in.read();
    }

    @Test
    public void test3() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executorService.execute(new Exercise1());
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws IOException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Random r = new Random(47);
        for(int i = 0; i < 20; i++) {
            executorService.execute(new Exercise2Fibonacci(r.nextInt(100)));
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
