package com.zh.string.codewars;

/**
 * Created by zhheng on 2016-04-23.
 */
public class ArrayPrinter {
    public static String printArray(Object array[]) {
        String tmps = "";
        for(Object i : array){
            tmps += String.valueOf(i) + ",";
        }
        return tmps.substring(0, tmps.length()-1);
    }
}
