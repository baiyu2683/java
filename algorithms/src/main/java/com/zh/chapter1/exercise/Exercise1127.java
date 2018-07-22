package com.zh.chapter1.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zh2683
 */
public class Exercise1127 {
    private static int num = 1;
    private static Map<String, Double> map = new HashMap<>();
    public static void main(String[] args) {
        binominal(10, 5, 0.25);
        System.out.println(num);
    }

    public static double binominal(int N, int k, double p) {
        num++;
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        int nextN = N - 1;
        String k1 = nextN + ", " + k;
        Double v1 = map.get(k1);
        if (v1 == null) {
            v1 = binominal(nextN, k, p);
            map.put(k1, v1);
        }
        String k2 = nextN + ", " + (k - 1);
        Double v2 = map.get(k2);
        if (v2 == null) {
            v2 = binominal(nextN, k - 1, p);
            map.put(k2, v2);
        }
        return (1.0 - p) * v1 + p * v2;
    }
}
