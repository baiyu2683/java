package com.zh.chapter1.chapter14;

import com.zh.chapter1.chapter11.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.net.URL;
import java.util.Arrays;

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
public class TwoSumFast {

    public static int count(int[] a) {
        int cnt = 0;
        Arrays.sort(a);
        for (int i = 0 ; i < a.length ; i++) {
            // 大于是为了排除重复选择
            if (BinarySearch.rank(-(a[i]), a) > i) {
                cnt++;
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
        URL url = TwoSumFast.class.getResource("/" + file);
        In in = new In(url);
        int[] a = in.readAllInts();
        long start = System.currentTimeMillis();
        System.out.println(count(a));
        System.out.println(System.currentTimeMillis() - start);
    }
}
