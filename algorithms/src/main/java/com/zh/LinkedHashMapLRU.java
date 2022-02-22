package com.zh;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用linkedHashMap的排序性质实现lru算法
 */
public class LinkedHashMapLRU<S, T> {

    private int capacity;
    private Object eldestKey;
    // 用于记录顺序和记录超过容量时的第一个元素
    private LinkedHashMap<S, Object> keys;
    private LinkedHashMap<S, T> items;

    public LinkedHashMapLRU() {
        capacity = 1024;
        items = new LinkedHashMap<S, T>(capacity);
        keys = new LinkedHashMap<S, Object>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (size() > capacity) {
                    eldestKey = eldest.getKey();
                    return true;
                }
                return false;
            }
        };
    }

    public void put(S key, T item) {
        keys.put(key, 1);
        if (eldestKey != null) {
            items.remove(eldestKey);
            eldestKey = null;
        }
        items.put(key, item);
    }

    public T get(S key) {
        // keys会将访问的元素放到末尾
        keys.get(key);
        return items.get(key);
    }

    public static void main(String[] args) {
        LinkedHashMapLRU<String, String> lru = new LinkedHashMapLRU<>();
        for (int i = 1 ; i <= 1024 ; i++) {
            lru.put(String.valueOf(i), "item" + i);
        }
        // 放入大于容量的数据，会移除第一个
        lru.put("1025", "item1025");
        // 测试，
        System.out.println(lru.get("1"));

        // 访问元素, 会将访问的元素放到末尾
        lru.get("2");
        System.out.println(lru.get("2"));
    }
}
