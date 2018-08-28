package com.zh.chapter1.chapter14;

import com.zh.chapter1.chapter11.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.net.URL;
import java.util.Arrays;

/**
 * 计算一个文件中所有和为0的三整数元组的数量，假设整数不会溢出
 *
 * 会进行N(N-1)/2 次 二分查找，则运行时间为N^2lgN
 */
public class ThreeSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = i + 1 ; j < N ; j++) {
                if (BinarySearch.rank(-(a[i] + a[j]), a) > j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        String file = "1Kints.txt";
//        file = "2Kints.txt";
//        file = "4Kints.txt";
        file = "8Kints.txt";
//        file = "16Kints.txt";
//        file = "32Kintx.txt";
        URL url = ThreeSumFast.class.getResource("/" + file);
        In in = new In(url);
        int[] a = in.readAllInts();
        long start = System.currentTimeMillis();
        System.out.println(count(a));
        System.out.println(System.currentTimeMillis() - start);
    }
}
