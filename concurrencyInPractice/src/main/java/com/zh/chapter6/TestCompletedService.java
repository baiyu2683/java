package com.zh.chapter6;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 这个CompletedService可以实现添加的任务，先执行完的先返回，而不是按添加顺序返回，有助于提高效率，
 * 当然总时间都是执行最长的任务的时间
 *  原理是封装了QueueingFuture，每个任务完成时调用done()方法，将任务加入完成队列，就可以take到了
 * Created by zh on 2017-08-07.
 */
public class TestCompletedService {

    static public class MyExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            new Thread(command).start();    //这么写的时候future.get会超时TimeoutException
//            command.run();                    //这么写的时候future.get不会超时
            // 这注释真的坑，run是同步的，根本执行不到future.get()...
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
            System.out.println(1);
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
            // 这个保证是异步的，submit之后立即返回，不然就是同步的了
            new Thread(task).start();
        });
        for (Callable<Integer> callable : list) {
            completionService.submit(callable);
        }
        s.setLength(0);
        for (int i = 0; i < list.size(); i++) {
            try {
                Future<Integer> future = completionService.take();
                Integer result = future.get();
                System.out.println("result: " + result);
                s.append(result + ", ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(s.toString());
    }
}
