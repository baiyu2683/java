package com.zh.arrays;

import java.util.Arrays;

/**
 * Created by zh on 2017-06-27.
 */
public class FillingArrays {
    public static void main(String[] args) {
        String[] a = new String[10];
        Arrays.fill(a, 0, 5, "x");
        System.out.println(Arrays.deepToString(a));
        Arrays.fill(a, 5, 10, "y");
        System.out.println(Arrays.deepToString(a));
    }
}
