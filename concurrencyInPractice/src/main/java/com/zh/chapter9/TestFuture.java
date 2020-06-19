package com.zh.chapter9;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-10-11.
 */
public class TestFuture {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(50));

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(400);
            System.out.println("111");
            return "1";
        });
        executor.submit(futureTask);
        try {
            String result = futureTask.get(10, TimeUnit.SECONDS);// 超时后，抛出异常，但是任务仍会继续执行
            System.out.println("结果: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            futureTask.cancel(true); //取消正在执行的任务。
        }
    }
}
