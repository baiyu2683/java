package com.zh.enumerated;

import com.zh.util.Enums;

/**
 * Created by zh on 2017-03-19.
 */
public class RoShamBo {
    public static <T extends Competitor<T>> void match(T a, T b){
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }
    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> tClass, int size) {
        for(int i = 0; i < size; i++) {
            match(Enums.random(tClass), Enums.random(tClass));
        }
    }
}
