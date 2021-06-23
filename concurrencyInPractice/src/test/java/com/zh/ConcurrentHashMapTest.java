package com.zh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测一下concurrentHashMap
 *
 * @author zh
 * 2018年9月12日
 */
public class ConcurrentHashMapTest {
    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) {
        map.put("10", "10");
    }
}
