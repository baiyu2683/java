package com.zh.chapter1.chapter11.exercise;

/**
 * 递归方法计算log(N!)
 * @Author zh2683
 */
public class Exercise1120 {
    public static void main(String[] args) {
        System.out.println(lgFac(0));
        System.out.println(lgFac(10));
        System.out.println(lgFac(1000));
        System.out.println(lgFac(5000));
    }

    private static double lgFac(int N) {
        if (N == 0) return Double.MIN_VALUE;
        else {
            return Math.log(N) + lgFac(N - 1);
        }
    }

}
