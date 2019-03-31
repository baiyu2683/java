package com.zh.concurrent.completablefuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 改良同步
 */
public class ShopPrices {

    private static List<Shop> shops;

    static {
        int count = 10;
        shops = new ArrayList<>(count);
        for (int i = 0 ; i < count ; i++) {
            shops.add(new Shop(String.valueOf(i)));
        }
    }

    /**
     * 接受产品名作为参数,返回一个字符串列表,包括商店名称,该商店中指定商品的价格
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        return shops.stream()
//                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .map(shop -> shop.getPriceDiscount(product)) // 折扣
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    /**
     * 使用并行流优化计算
     * @param product
     * @return
     */
    public List<String> findPricesParalle(String product) {
        return shops.parallelStream()
//                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))) // 普通
                .map(shop -> shop.getPriceDiscount(product)) // 折扣
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    /**
     * 使用CompletableFuture优化计算
     * @param product
     * @return
     */
    public List<String> findPricesComplatableFuture(String product) {
        List<CompletableFuture<String>> futures =
                shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceDiscount(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                    CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))
                ))
                .collect(toList());
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    private static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });
    /**
     * 使用CompletableFuture优化计算
     *
     * 改进executor
     * @param product
     * @return
     */
    public List<String> findPricesComplatableFuture2(String product) {
        List<CompletableFuture<String>> futures =
                shops.stream()
//                        .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceDiscount(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        // thenCompose 将前一个future的输出作为下一个函数的输入(quote)
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                        ))
                        .collect(toList());
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 以上的都是所有的操作都完成才返回
     *
     * 此方法实现每完成一个就返回, 使用completion实现
     * @param product
     * @return
     */
    public Stream<CompletableFuture<String>> findPricesComplatableStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceDiscount(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }
}
