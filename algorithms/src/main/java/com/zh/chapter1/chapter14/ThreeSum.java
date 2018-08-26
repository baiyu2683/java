package com.zh.chapter1.chapter14;

import edu.princeton.cs.algs4.In;

import java.net.URL;

/**
 * 计算一个文件中所有和为0的三整数元组的数量，假设整数不会溢出
 *  Data files:   https://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/1Mints.txt
 */
public class ThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = i + 1 ; j < N ; j++) {
                for (int k = j + 1 ; k < N ; k++) {
                    if (a[i] + a[j] + a[k] == 0)
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
        URL url = ThreeSum.class.getResource("/" + file);
        In in = new In(url);
        int[] a = in.readAllInts();
        long start = System.currentTimeMillis();
        System.out.println(count(a));
        System.out.println(System.currentTimeMillis() - start);
    }
}
