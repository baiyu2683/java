package com.zh.chapter6;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-08-07.
 */
public class TestCompletedService {

    static public class MyExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
//            new Thread(command).start();    //这么写的时候future.get会超时TimeoutException
            command.run();                    //这么写的时候future.get不会超时
        }
    }

    @Test
    public void test1() {
        CompletionService<String> completionService = new ExecutorCompletionService<String>(new MyExecutor());
        Future<String> future = completionService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            for(;;);
        }, "zh's test case");
        try {
            System.out.println(future.get(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
