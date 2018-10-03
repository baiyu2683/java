package com.zh;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

/**
 * lru算法
 * @Author zh2683
 */
public class LRU<T> {

    private int capacity = 10;

    private LinkedHashMap<String, T> items = new LinkedHashMap<>(capacity);

    public LRU(int capacity) {
        this.capacity = capacity;
    }


    public boolean put(String key, T value) {
        T oldValue = items.get(key);
        if (oldValue != null) {
            items.put(key, oldValue);
        } else if (items.size() == capacity){
            Iterator<String> iterator = items.keySet().iterator();
            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }
        items.put(key, value);
        return true;
    }

    public T get(String key) {
        T value = items.get(key);
        // 最近访问的放在最后
        if (value !=  null) {
            items.remove(key);
            items.put(key, value);
        }
        return value;
    }

    public static void main(String[] args) {
        LRU<String> lru = new LRU<>(10);
        for (int i = 0 ; i < 20 ; i++) {
            lru.put(String.valueOf(i), (int)Math.floor(Math.random() * 100) + "");
        }
        // 访问18和19，令其不会被淘汰
        lru.get(19 + "");
        lru.get(18 + "");
        // 重新放入元素
        lru.put("21", "21");
        lru.put("22", "22");
        // 验证18和19没有被淘汰
        System.out.println(lru.get("19"));
        System.out.println(lru.get("18"));
    }
}
