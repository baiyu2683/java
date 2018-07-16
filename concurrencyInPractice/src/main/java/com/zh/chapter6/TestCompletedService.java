package com.zh.chapter6;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Test
    public void testCompletionService() {
        Random random = new Random(10);
        List<Callable<Integer>> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for (int i = 0 ; i < 10 ; i++) {
            int sleeptime = new BigDecimal(Math.random() * 10).intValue();
            s.append(sleeptime + ", ");
            list.add(() -> {
                TimeUnit.SECONDS.sleep(sleeptime);
                return sleeptime;
            });
        }
        System.out.println(s.toString());
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(task -> {
            new Thread(task).start();
        });
        for (Callable<Integer> callable : list) {
            completionService.submit(callable);
        }
        s.setLength(0);
        for (int i = 0; i < list.size(); i++) {
            try {
                Future<Integer> future = completionService.take();
                s.append(future.get() + ", ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(s.toString());
    }
}
