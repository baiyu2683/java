package com.zh.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import java.io.Serializable;

public class EhcacheTest {

    public static void main(String[] args) {
        // 1. 初始化一个缓存管理器
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(System.getProperty("java.io.tmpdir")))
                .build(true);

        // 2. 缓存配置项
        CacheConfiguration cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Serializable.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(100, EntryUnit.ENTRIES)
                                .disk(10, MemoryUnit.GB, true)
                                .build())
                .build();
        // 2.1. 新建一个缓存preConfigured
        Cache<String, String> preConfigured = cacheManager.createCache("preConfigured", cacheConfiguration);
        preConfigured.put("1", "1000000");
        System.out.println(preConfigured.get("1"));

        // 2.2. 新建一个缓存mycache
        Cache<String, String> myCache = cacheManager.createCache("mycache", cacheConfiguration);
        myCache.put("10", "ssss");
        System.out.println(myCache.get("10"));
        System.out.println(myCache.get("1"));
    }
}
