package com.zh.chapter1.chapter14.exercise;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 找到给定的含有N个double值的数组a[]中一对最接近的值
 * 要求最坏情况下运行时间是NlogN,即线性对数级别的
 * 一种解法: 先排序，依次计算相邻大小
 */
public class Exercise1416 {

    public static double[] findMinDistance(double[] a) {
        // 排序: logn
        Arrays.sort(a);
        // 存储相邻的差值
        double[] b = new double[a.length - 1];
        // 计算相邻的差值: n
        for (int i = 1 ; i < a.length ; i++) {
            b[i - 1] = Math.abs(a[i] - a[i - 1]);
        }
        // 得到b中最小值: n
        double min = Double.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0 ; i < b.length ; i++) {
            if (min > b[i]) {
                min = b[i];
                minIndex = i;
            }
        }
        double[] result = new double[2];
        result[0] = a[minIndex];
        result[1] = a[minIndex + 1];
        return result;
    }

    public static void main(String[] args) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        double[] a = new double[100000000];
        for (int i = 0 ; i < a.length ; i++) {
            a[i] = tlr.nextDouble();
        }

        long start = System.currentTimeMillis();
        double[] result = findMinDistance(a);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(Arrays.toString(result));
    }
}
//13049
//[0.481617551452882, 0.4816175514528821]