package com.zh;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zh on 2017-07-31.
 */
public class TestExecutors {

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Executor executor = new MyExecutor();
        executor.execute(() -> System.out.println(1));
    }

    static class MyExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
