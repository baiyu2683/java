package com.zh.collection;

import java.util.ArrayList;
import java.util.Collection;

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
}
