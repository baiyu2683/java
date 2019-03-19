package com.zh;

import com.alibaba.fastjson.JSONObject;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * 一致性hash算法
 * @author zh2683
 */
public class ConsistentHash {

    private static final HashFunction hashFunction = Hashing.murmur3_128(128);

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        TreeMap<Integer, String> source = new TreeMap<>();
        source.put(keyGenerator(ip), "193.112.95.23");

        ip = "127.9.9.9";
        source.put(keyGenerator(ip), "193.112.95.23");

        ip = "177.0.0.1";
        source.put(keyGenerator(ip), "193.112.95.20");

        System.out.println(JSONObject.toJSONString(source));

    }

    private static int keyGenerator(String value) {
        return hashFunction.hashString(value, Charset.forName("utf-8")).asInt();
    }

}
