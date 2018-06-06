package com.zh.generics;

import java.util.List;

/**
 * Created by zh on 2017-08-29.
 */
public class TestCover {
    public static void main(String[] args) {

    }
}

class Bean1 {}
class Bean2 extends Bean1 {}

class D<T> {
    public <S extends T> List<S> show(S s) {
        System.out.println(s);
        return null;
    }
}
