package com.zh.generics;

import java.util.*;

/**
 * TODO 不知道为什么注释的行有报错
 * 测试嵌套的无界通配符: ?
 * @author zh2683
 */
public class TestNoLimitSignal {

    public static void main(String[] args) {
        Map<String, List<Integer>> map = new HashMap<>();
        testMap(map);
//        testMap2(map); // 报错...

        Map<String, List<?>> map1 = new HashMap<>();
        testMap2(map1);

        List<?> list = new ArrayList<String>();
        testList(list);
    }

    private static void testMap(Map<?, ? extends List<?>> map) {
    }

    private static void testMap2(Map<?, List<?>> map) {
    }

    private static void testList(List<?> list) {
    }
}
