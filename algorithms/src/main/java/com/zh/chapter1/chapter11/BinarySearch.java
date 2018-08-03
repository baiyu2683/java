package com.zh.chapter1.chapter11;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import utils.IntGenerator;

import java.util.Arrays;

/**
 * 二分查找
 * @Author zh2683
 */
public class BinarySearch {

    /**
     *
     * @param k 查找的元素
     * @param a 有序的数组
     * @return
     */
    public static int rank(int k, int[] a) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < k) low = mid + 1;
            else if (a[mid] > k) high = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = IntGenerator.generatorIntArr(100);
        Arrays.sort(whitelist);
        StdOut.println(Arrays.toString(whitelist));
        while(!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            // 不存在的打印
            if (rank(key, whitelist) < 0)
                StdOut.println(key);
        }
    }
}
