package com.zh.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 */
public class FlyWeightFactory {
    // 享元池
    private static Map<String, FlyWeight> cache = new HashMap<>();

    public synchronized static FlyWeight get(String state) {
        FlyWeight flyWeight = cache.get(state);
        if (flyWeight == null) {
            flyWeight = new ConcreateFlyWeight(state);
            cache.put(state, flyWeight);
        }
        return flyWeight;
    }

    public static int size() {
        return cache.size();
    }
}
