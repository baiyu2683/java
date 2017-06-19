package com.zh.generics;

import com.zh.util.Generator;

/**
 * 自动包装机制不能应用于数组
 * Created by zh on 2017-06-19.
 */
public class PrimitiveGenericTest {
    public static void main(String[] args) {
        int[] a = new int[10];
        Integer[] b = new Integer[1];
        //int[] c = new Integer[1];
        //Integer[] d = new int[1];
    }
}

class FArray {
    public static <T> T[] fill(T[] a, Generator<T> generator) {
        for (int i = 0; i < a.length; i++)
            a[i] = generator.next();
        return a;
    }
}
