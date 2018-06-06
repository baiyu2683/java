package com.zh.arrays;

import java.util.Arrays;

/**
 * Created by zhangheng on 2017/6/27.
 */
public class ThreeDWithNew {
    public static void main(String[] args) {
        int[][][] a = new int[2][2][4];
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.deepToString(a));
    }
}
