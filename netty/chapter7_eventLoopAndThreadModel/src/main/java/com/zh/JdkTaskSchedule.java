package com.zh;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * jdk任务调度
 * 任务多时有动态创建线程的过程，也有线程上下文切换，负载高时会成为瓶颈
 */
public class JdkTaskSchedule {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> future = executorService.schedule(() -> {
            System.out.println("60 seconds later");
        }, 60, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
