package com.zh.util;

import java.util.Arrays;

/**
 * Created by zh on 2016/12/18.
 */
public class Fibonacci {

    public static double[] getFibonacci(int n) {
        double[] fibonacci = new double[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1)
                fibonacci[i] = 1;
            else
                fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    public static double getFibonacciAdd(int n ){
        double[] fibonacci = getFibonacci(n);
        return Arrays.stream(fibonacci).reduce(0, (x, y) -> x + y);
    }
}
