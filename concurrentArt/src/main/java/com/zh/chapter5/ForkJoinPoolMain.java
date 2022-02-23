package com.zh.chapter5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolMain {

    /**
     * 有返回值的task
     */
    private static class Task1 extends RecursiveTask<Integer> {

        @Override
        protected Integer compute() {
            return null;
        }
    }

    private static class Task2 extends RecursiveAction {

        @Override
        protected void compute() {

        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> future = forkJoinPool.submit(new Task1());
        forkJoinPool.submit(new Task2());
    }
}
