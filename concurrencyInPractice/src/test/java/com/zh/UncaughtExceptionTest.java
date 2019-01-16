package com.zh;

import org.junit.Test;

import javax.management.relation.RoleUnresolved;
import java.util.concurrent.*;

public class UncaughtExceptionTest {

    @Test
    public void test1() throws InterruptedException, ExecutionException {
//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//            System.out.println(t.getName());
//            e.printStackTrace();
//        });
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("asdf");
                thread.setUncaughtExceptionHandler((t, e) -> {
                    System.out.println(t.getName());
                    e.printStackTrace();
                });
                return thread;
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                10, TimeUnit.SECONDS,  new LinkedBlockingQueue<>(1), threadFactory);
        // 异常会被UncaughtException捕获
        executor.execute(() -> {
           int i = 1 / 0;
        });
        // 不会被捕获
        executor.submit(() -> {
            int i = 1 / 0;
        });
    }
}
