package com.zh.generics;

/**
 * Created by zh on 2017-06-25.
 */
public class Unconstrained {
    public static void main(String[] args) {
        BasicOther b = new BasicOther(), b2 = new BasicOther();
        b.set(new Other());
        Other o = b.get();
        b.f();
    }
}
class Other {}
class BasicOther extends BasicHolder<Other> {}
