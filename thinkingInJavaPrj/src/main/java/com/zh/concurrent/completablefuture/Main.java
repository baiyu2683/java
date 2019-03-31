package com.zh.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Main {

    @Test
    public void futureTest() throws ExecutionException, InterruptedException {
        // 测试异步
        Shop shop = new Shop("");
        Future<Double> future = shop.getPriceAsync("10");
        System.out.println(future.get());
    }

    @Test
    public void futureTest2() {
        StopWatch stopWatch = new StopWatch();
        ShopPrices shopPrices = new ShopPrices();

//        stopWatch.start();
//        List<String> stringList = shopPrices.findPrices("123");
//        System.out.println("花费时间: " + stopWatch.getTime());

        stopWatch.reset();
        stopWatch.start();
        shopPrices.findPricesParalle("123");
        System.out.println("花费时间: " + stopWatch.getTime());

        stopWatch.reset();
        stopWatch.start();
        shopPrices.findPricesComplatableFuture("123");
        System.out.println("花费时间: " + stopWatch.getTime());

        stopWatch.reset();
        stopWatch.start();
        shopPrices.findPricesComplatableFuture2("123");
        System.out.println("花费时间: " + stopWatch.getTime());
    }

    @Test
    public void testThenCombined() throws ExecutionException, InterruptedException {
        Future<Double> future =
                CompletableFuture.supplyAsync(() -> getPrice())
                .thenCombine(CompletableFuture.supplyAsync(() -> 10),  // 将两个future的结果传给函数合并计算
                        (before, after) -> {
                            System.out.println("before: " + before);
                            System.out.println("after: " + after);
                            return before * after;
                        });
        System.out.println(future.get());
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    private static double getPrice() {
        delay();
        return ThreadLocalRandom.current().nextDouble();
    }

    @Test
    public void testComplation() {
        ShopPrices shopPrices = new ShopPrices();
        Stream<CompletableFuture<String>> stream = shopPrices.findPricesComplatableStream("123");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 每完成一个都会调用thenAccept方法进行计算,不需要等待所有都完成
        CompletableFuture[] futures = stream.map(future -> future.thenAccept(price -> {
            System.out.println("耗时: " + stopWatch.getTime() + ", 价格: " + price);
        }))
        .toArray(size -> new CompletableFuture[size]);

        // 等待所有的都完成
        CompletableFuture.allOf(futures).join();
        System.out.println("所有的都完成了");
    }

}
