package com.zh.chapter8;

import java.util.concurrent.*;

/**
 * Created by zh on 2017-09-25.
 */
public class ThreadDeadlock {
    private static ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            //将发生死锁 --- 由于任务在等待子任务的结果
            return header.get() + page + footer.get();
        }

        public String renderBody() {
            return "body";
        }
    }

    public class LoadFileTask implements Callable<String> {
        private String path;
        public LoadFileTask(String path) {
            this.path = path;
        }
        @Override
        public String call() {
            System.out.println(Thread.currentThread().getName() + "-" + path);
            return "file";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDeadlock threadDeadlock = new ThreadDeadlock();
        Future<String> future = exec.submit(threadDeadlock.new RenderPageTask());
        System.out.println(future.get());
    }
}
