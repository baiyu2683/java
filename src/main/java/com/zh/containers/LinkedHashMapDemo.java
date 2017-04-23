package com.zh.containers;

import java.util.LinkedHashMap;

/**
 * Created by zh on 2017-04-23.
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, String> linkedHashMap1 = new LinkedHashMap<>();
        char a = '@';
        for(int i = 0; i < 10; i++) {
            a += 1;
            linkedHashMap.put(i, a + "0");
            linkedHashMap1.put(i, a + "0");
        }
        System.out.println(linkedHashMap);
        //第三个参数是是否按访问顺序排序，为true时访问过的会放在后面(LRU)
        linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.putAll(linkedHashMap1);
        System.out.println(linkedHashMap);
        for (int i = 0; i < 6; i++) //访问元素
            linkedHashMap.get(i);
        System.out.println(linkedHashMap);
        linkedHashMap.get(0);
        System.out.println(linkedHashMap);
        //从输出结果看访问过的元素被放在最后面了
    }
}
