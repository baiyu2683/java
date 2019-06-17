package com.zh.concurrent.forkjoin;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Administrator
 * @date 2019/06/17
 */
public class TestForkJoinPool {

    private static Map<String, Integer> map = new ConcurrentHashMap<>();


    @Test
    public void first() throws InterruptedException, TimeoutException, ExecutionException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        PrintTask printTask = new PrintTask(0, 1000);
        Future<Integer> future = forkJoinPool.submit(printTask);
        System.out.println(future.get(2, TimeUnit.SECONDS));
    }

    static class PrintTask extends RecursiveTask<Integer> {

        private Integer start;
        private Integer end;
        private static int THRESHOLD = 20;

        public PrintTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < THRESHOLD) {
                int sum = 0;
                for (int i = start ; i < end ; i++) {
                    String threadName = Thread.currentThread().getName();
                    map.putIfAbsent(threadName, 0);
                    System.out.println(threadName + ", " + i);
                    sum += i;
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                PrintTask left = new PrintTask(start, middle);
                PrintTask right = new PrintTask(middle, end);

                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }

}
