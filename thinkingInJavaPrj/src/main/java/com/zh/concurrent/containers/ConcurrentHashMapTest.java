package com.zh.concurrent.containers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author zh
 * 2018年9月12日
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
//        map.put(null, null);
        map = new HashMap<>();
        map.put(null, null);
    }
}
