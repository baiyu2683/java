package com.zh.arrays;

/**
 * Created by zhangheng on 2017/6/27.
 */
public class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    public String toString() {
        return "Sphere " + id;
    }
}
