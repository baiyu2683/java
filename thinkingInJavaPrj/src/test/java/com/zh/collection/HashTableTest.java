package com.zh.collection;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * 测试hashtable问题
 *
 * @author zh
 * 2018年9月20日
 */
public class HashTableTest {

    public static void main(String[] args) {
        Hashtable<String, String> tab = new Hashtable<>();
        tab.put("1", "1");
        tab.put("2", "2");
        Iterator<String> keyIterator = tab.keySet().iterator();
        while(keyIterator.hasNext()) {
            String key = keyIterator.next();
            System.out.println(key);
            System.out.println(tab);
            keyIterator.remove();
            System.out.println(tab);
        }
    }
}
