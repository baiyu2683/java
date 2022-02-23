package com.zh.map;


import java.util.HashMap;

public class HashMapMain {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 默认容量16,加载因子0.75, 则超过12个就扩容
        for (int i = 1 ; i <= 12 ; i++) {
            map.put(i, i);
        }
        // 触发扩容
        map.put(13, 13);
        map.get(13);
    }
}
