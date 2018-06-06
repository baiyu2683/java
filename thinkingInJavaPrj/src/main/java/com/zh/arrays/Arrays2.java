package com.zh.arrays;

/**
 * Created by zh on 2017-06-27.
 */
public class Arrays2 {
    public static void main(String[] args) {

    }
    private static BerylliumSphere[] generatorArr(int size) {
        BerylliumSphere[] arr = new BerylliumSphere[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new BerylliumSphere();
        }
        return arr;
    }
}
