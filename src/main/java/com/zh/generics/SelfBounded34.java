package com.zh.generics;

/**
 * Created by zh on 2017-06-25.
 */
public abstract class SelfBounded34<T extends SelfBounded34<T>> {
    private T element;

    public void setElement(T element) {this.element = element;}

    public abstract void show(T arg);

    public String toString() {
        return element.getClass().getSimpleName();
    }
}
