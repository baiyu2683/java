package com.zh.arrays;

import java.util.Arrays;

/**
 * Created by zh on 2017-06-27.
 */
public class CopyingArrays {
    public static void main(String[] args) {
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i, 47);
        Arrays.fill(j, 99);
        System.out.println("i = " + Arrays.toString(i));
        System.out.println("j = " + Arrays.toString(j));
        System.arraycopy(i, 0, j, 0, i.length);
        System.out.println("j = " + Arrays.toString(j));

        Integer[] k = new Integer[10];
        Arrays.fill(k, 100);
        System.arraycopy(i, 0, k, 2, i.length); //数组类型必须相同，不会自动包装和拆包
        System.out.println("k = " + Arrays.toString(k));
    }
}
