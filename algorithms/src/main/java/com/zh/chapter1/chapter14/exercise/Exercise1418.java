package com.zh.chapter1.chapter14.exercise;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 数组的局部最小元素
 */
public class Exercise1418 {

    private static int findMin(double[] arr, int leftIndex, int rightIndex) {
        int midIndex = (leftIndex + rightIndex) / 2;

        if (midIndex == 0) return -1;

        double left = arr[midIndex - 1];
        double mid = arr[midIndex];
        double right = arr[midIndex + 1];
        if (mid < left && mid < right) return midIndex;
        else {
            if (left < right) return findMin(arr, leftIndex, midIndex - 1);
            else return findMin(arr, midIndex + 1, rightIndex);
        }
    }

    public static void main(String[] args) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        double[] a = new double[10];
        for (int i = 0 ; i < a.length ; i++) {
            a[i] = tlr.nextDouble(1, 10000);
        }
        int index = findMin(a, 0, a.length - 1);
        if (index == -1) System.out.println(-1);
        else System.out.println(index + "-" + a[index]);
    }
}
