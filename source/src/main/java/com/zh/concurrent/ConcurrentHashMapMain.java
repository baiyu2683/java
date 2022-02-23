package com.zh.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapMain {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 1);
    }
}
