package com.zh.concurrent;

import com.zh.util.Fibonacci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zh on 2016/12/18.
 */
public class Exercise2Fibonacci implements Runnable {
    private int n;

    public Exercise2Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        double[] fibonacci = Fibonacci.getFibonacci(n);
        System.out.println(Arrays.toString(fibonacci));
    }
}
