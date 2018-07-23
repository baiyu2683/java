package com.zh.chapter1.exercise;

import java.util.Random;

/**
 * @Author zh2683
 */
public class Exercise1139 {

    public static void main(String[] args) {
        int T = 10;
        int[] NS = {10 * 10 * 10, 10 * 10 * 10 * 10, 10 * 10 * 10 * 10 * 10, 10 * 10 * 10 * 10 * 10 * 10};
        for (int j = 0 ; j < NS.length ; j++) {
            int n = NS[j];
            int num = 0;
            for (int i = 0 ; i < T ; i++) {
                num += action(NS[j]);
            }
            System.out.println(n + ": " + (num / T));
        }
    }

    public static int action(int N) {
        int[] a1 = new int[N];
        for (int i = 0 ; i < N ; i++) {
            a1[i] = Double.valueOf(Math.round(Math.random() * 10 * 10 * 10 * 10 * 10 * 10)).intValue();
        }
        int num = 0;
        for (int i = 0 ; i < N ; i++) {
            int next = Double.valueOf(Math.round(Math.random() * 10 * 10 * 10 * 10 * 10 * 10)).intValue();
            for (int j = 0 ; j < N ; j++) {
                if (next == a1[j]) {
                    num++;
                    break;
                }
            }
        }
        return num;
    }
}
