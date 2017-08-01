package com.zh;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-07-31.
 */
public class TestExecutors {

    @Test
    public void test1() {
        Executor executor = new MyExecutor();
        executor.execute(() -> System.out.println(1));
    }

    static class MyExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    @Test
    public void TestFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "completed";
        });
        System.out.println(future.get());
    }
}
