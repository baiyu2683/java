package com.zh.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * 测试Collection
 *
 * @author zh
 * 2018年8月13日
 */
public class TestCollection {

    @Test
    public void testCollection() {
        Collection<String> collection = new ArrayList<>();
        collection.add("123");
        System.out.println(collection.toString());
        collection.remove("123");
        System.out.println(collection.toString());
        collection.clear();
    }
    
    @Test
    public void testFastFail() {
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            list.add(i + "");
        }
        for (String s : list) {
            list.add("12");
        }
    }
}
