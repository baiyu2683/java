package com.zh.generics;

/**
 * Created by zh on 2017-06-25.
 */
public class BasicHolder<T> {
    T element;
    void set(T arg) { element = arg; }
    T get() { return element; }
    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}
