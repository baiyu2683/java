package com.zh.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zh on 2017-06-27.
 */
public class StringSorting {
    public static void main(String[] args) {
        String[] sa = new String[10];
        int index = (new Random(47)).nextInt(10);
        for (int i = 0; i < sa.length; i++) {
            sa[i] = UUID.randomUUID().toString().replaceAll("-", "");
            if (i % 2 == 0) sa[i] = sa[i].toUpperCase();
        }
        String s = sa[index];
        System.out.println(Arrays.toString(sa));
        Arrays.sort(sa);
        System.out.println(Arrays.toString(sa));
        Arrays.sort(sa, Collections.reverseOrder());
        System.out.println(Arrays.toString(sa));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(sa));

        //使用binarySearch时，如果不是按照自然排序的，需要提供排序的Comparator
        System.out.println(s);
        System.out.println(Arrays.binarySearch(sa, s)); //不提供所使用的排序方法，有可能查不到
        System.out.println(Arrays.binarySearch(sa, s, String.CASE_INSENSITIVE_ORDER));

    }
}
