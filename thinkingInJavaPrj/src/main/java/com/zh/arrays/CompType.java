package com.zh.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by zh on 2017-06-27.
 */
public class CompType implements Comparable<CompType> {
    int i;
    int j;
    private static int count = 1;
    public CompType(int n1, int n2) {
        i = n1;
        j = n2;
    }
    public String toString() {
        String result = "[i = " + i + ", j = " + j + "]";
        if(count++ % 3 == 0) result += "\n";
        return result;
    }
    @Override
    public int compareTo(CompType o) {
        return (i < o.i? -1 : (i == o.i? 0: 1));
    }

    public static void main(String[] args) {
        Random r = new Random(47);
        CompType[] x = new CompType[20];
        for (int i = 0; i < x.length; i++)
            x[i] = new CompType(r.nextInt(100), r.nextInt(100));
        Arrays.sort(x);
        System.out.println(Arrays.toString(x));
        Arrays.sort(x, Collections.reverseOrder());
        System.out.println(Arrays.toString(x));
    }
}
