package com.zh;

import java.util.Arrays;

import utils.IntGenerator;

public class ShellTest {

    public static void main(String[] args) {
        Integer[] a = IntGenerator.generatorIntegerArr(1024);
        int len = a.length;
        int h = 1;
        int step = 4;
        while (h < len / step) {
            h = h * step + 1;
        }
        while (h > 0) {
            for (int i = h ; i < len ; i++) {
                for (int j = i ; j >= h ; j -= h) {
                    if (a[j] < a[j - 1]) {
                        Integer t = a[j];
                        a[j] = a[j - 1];
                        a[j - 1] = t;
                    }
                }
            }
            h /= step;
        }
        System.out.println(Arrays.toString(a));
    }
}
