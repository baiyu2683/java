package com.zh.chapter1.chapter14.exercise;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 给定一个含有N个double值的数组a,找到相差最大的两个数
 * 要求最坏情况下运行时间是N，即线性级别的
 * 一种解法: 将数组分为左右两部分，
 *          依次向右遍历每个元素，并求当前元素和左边数组中的最小值的最大差值并和当前的结果比较
 */
public class Exercise1417 {

    public static double[] findMaxDistance(double[] a) {
        double firstNum = a[0];
        double secondNum = a[1];

        double leftMinValue = firstNum < secondNum ? firstNum : secondNum;
        int minIndex = firstNum < secondNum ? 0 : 1;
        int maxIndex = firstNum < secondNum ? 1 : 0;
        double maxDistance = Math.abs(firstNum - secondNum);

        for (int i = 2 ; i < a.length ; i++) {
            double current = a[i];
            double distance = Math.abs(current - leftMinValue);
            leftMinValue = current > leftMinValue ? leftMinValue : current;
            minIndex = current > leftMinValue ? minIndex : i;
            maxIndex = current > leftMinValue ? i : maxIndex;
            maxDistance = distance > maxDistance ? distance : maxDistance;
        }

        double[] result = new double[2];
        result[0] = a[minIndex];
        result[1] = a[maxIndex];
        return result;
    }

    public static void main(String[] args) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        double[] a = new double[100000000];
        for (int i = 0 ; i < a.length ; i++) {
            a[i] = tlr.nextDouble();
        }

        long start = System.currentTimeMillis();
        double[] result = findMaxDistance(a);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(Arrays.toString(result));
    }
}
