package com.zh.chapter1.chapter11.exercise;

/**
 * 接受一个整数N, 返回不大于log2N的最大整数
 * @Author zh2683
 */
public class Exercise1114 {
    public static void main(String[] args) {
        System.out.println(lg(1000));
        System.out.println(lg(10));
        System.out.println(lg(10000));
    }

    private static int lg(int N) {
        int result = 1;
        for (int i = 0 ; i <= Integer.MAX_VALUE ; i++) {
            if (pow(i) <= N) result = i;
            else break;
        }
        return result;
    }

    /**
     * 返回2的n次方
     * @param n
     * @return
     */
    private static int pow(int n) {
        int result = 1;
        for (int i = 1 ; i <= n ; i++) {
            result *= 2;
        }
        return result;
    }
}
