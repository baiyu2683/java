package com.zh.chapter1.chapter11.exercise;

import java.util.Arrays;

/**
 * @Author zh2683
 */
public class Exercise1135 {

    public static void main(String[] args) {
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1 ; i <= SIDES ; i++) {
            for (int j = 1 ; j <= SIDES ; j++) {
                dist[i + j] += 1.0;
            }
        }
        for (int k = 2 ; k <= 2 * SIDES ; k++) {
            dist[k] /= 36.0;
        }
        System.out.println(Arrays.toString(dist));
    }
}
