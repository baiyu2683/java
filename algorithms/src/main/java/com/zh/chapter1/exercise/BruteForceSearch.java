package com.zh.chapter1.exercise;

import com.zh.chapter1.BinarySearch;
import utils.IntGenerator;

/**
 * @Author zh2683
 */
public class BruteForceSearch {

    public static void main(String[] args) {
        int[] arr = IntGenerator.generatorIntArr(5000000);
        int k = 10;

        long start = System.currentTimeMillis();
        BinarySearch.rank(k, arr);
        long mid = System.currentTimeMillis();
        search(k, arr);
        long end = System.currentTimeMillis();
        System.out.println(mid - start);
        System.out.println(end - mid);
    }

    private static int search(int k, int[] arr) {
        for (int i = 0 ; i < arr.length ; i++) {
            if (k == arr[i]) return k;
        }
        return -1;
    }
}
