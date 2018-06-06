package com.zh.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017-06-18.
 */
public class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
    }

    public static void main(String[] args) {
        List<? super Apple> ap = new ArrayList<>();
        ap.add(new Apple());
    }
}
