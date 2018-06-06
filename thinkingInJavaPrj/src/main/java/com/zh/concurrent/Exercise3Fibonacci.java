package com.zh.concurrent;

import com.zh.util.Fibonacci;

import java.util.concurrent.Callable;

/**
 * Created by zh on 2016/12/18.
 */
public class Exercise3Fibonacci implements Callable<Double> {
    private int n;

    public Exercise3Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public Double call() {
        return Fibonacci.getFibonacciAdd(n);
    }
}
