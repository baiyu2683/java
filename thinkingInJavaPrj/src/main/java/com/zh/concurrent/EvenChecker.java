package com.zh.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zh on 2016/12/25.
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    //final必须在构造函数里或者声明时初始化
    private final int id;
    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }
    @Override
    public void run() {
        while(!generator.isCanceled()) {
            int val = generator.next();
            if(val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancel();
            }
        }
    }
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(gp, i));
        }
        executorService.shutdown();
    }
    public static void test(IntGenerator gp){
        test(gp, 10);
    }
}
