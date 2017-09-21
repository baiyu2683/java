package com.zh.chapter7;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-09-21.
 */
public class FutureCancel {

    private static ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timeRun(Runnable r, long timeout, TimeUnit timeUnit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, timeUnit);
        } catch (ExecutionException e) {
            //重新抛出执行异常
            throw new RuntimeException(e.getCause());
        } catch (TimeoutException e) {
            //超时任务取消
            System.out.println("task execute timeout");
        } finally {
            task.cancel(true); //如果任务正在执行将被中断
            System.out.println("interrupting");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        timeRun(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
    }
}
