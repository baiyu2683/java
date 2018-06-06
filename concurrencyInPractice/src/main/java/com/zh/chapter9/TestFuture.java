package com.zh.chapter9;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-10-11.
 */
public class TestFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("111");
            return "1";
        });
        executorService.submit(futureTask);
        try {
            String result = futureTask.get(2, TimeUnit.SECONDS);// 超时后，抛出异常，但是任务仍会继续执行
            System.out.println(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            futureTask.cancel(true); //取消正在执行的任务。
        }

    }

    public static void main1(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future future = executorService.submit(() -> {
            try {
//                TimeUnit.SECONDS.sleep(2);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        });
        try {
            future.get(4, TimeUnit.SECONDS);
            System.out.println(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
