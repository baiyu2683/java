package com.zh.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestGuavaCache {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int expire = (int)(Math.random() * 10);
        while (expire <= 0) {
            expire = (int) (Math.random() * 10);
        }
        System.out.println(expire);
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expire, TimeUnit.SECONDS)
                .initialCapacity(10)
                .weakKeys()
                .build();
        cache.put("zhangheng", "zhangheng");
        TimeUnit.SECONDS.sleep(expire / 2);
        String value = cache.get("zhangheng", () -> "1");
        System.out.println("首次：" + value);
        TimeUnit.SECONDS.sleep(expire);
        value = cache.get("zhangheng", () -> "1");
        System.out.println("第二次：" + value);
    }
}
