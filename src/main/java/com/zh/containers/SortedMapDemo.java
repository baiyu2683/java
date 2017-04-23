package com.zh.containers;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * SortedMap例子
 * Created by zh on 2017-04-23.
 */
public class SortedMapDemo {
    public static void main(String[] args) {
        char a = '@';
        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        for(int i = 0; i < 10; i++) {
            sortedMap.put(i, (++a) + "0");
        }
        System.out.println(sortedMap);
        Integer low = sortedMap.firstKey();
        Integer high = sortedMap.lastKey();
        System.out.println(low);
        System.out.println(high);
        Iterator<Integer> it = sortedMap.keySet().iterator();
        for(int i = 0; i <= 6; i++) {
            if(i == 3) low = it.next();
            if(i == 6) high = it.next();
            else it.next(); //当i等于3的时候走了两次next，所以最后high是7
        }
        System.out.println();
        System.out.println(low);
        System.out.println(high);
        System.out.println(sortedMap.subMap(low, high));
        System.out.println(sortedMap.headMap(high));
        System.out.println(sortedMap.tailMap(low));
    }
}
