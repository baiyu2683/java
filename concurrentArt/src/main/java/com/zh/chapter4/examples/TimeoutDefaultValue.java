package com.zh.chapter4.examples;

import java.util.concurrent.*;

/**
 * 调用一个方法时等待一段时间(一般来说是给
 * 定一个时间段),如果该方法能够在给定的时间段之内得到结果,那么将结果立刻返回,反之,
 * 超时返回默认结果。
 */
public class TimeoutDefaultValue {
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    /**
     * 调用超时时返回某个默认值
     * @param timeout 秒
     */
    private String method(long timeout) {
        try {
            Future<String> future = executorService.submit(new CallerTask());
            return future.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            return "default";
        }
        return null;
    }

    static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(5);
            return "xxx";
        }
    }

    public static void main(String[] args) {
        TimeoutDefaultValue timeoutDefaultValue = new TimeoutDefaultValue();
        new Thread(() -> {
            System.out.println(timeoutDefaultValue.method(2));
        }).start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
