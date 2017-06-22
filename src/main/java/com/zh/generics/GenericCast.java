package com.zh.generics;

import java.util.ArrayList;

/**
 * Created by zh on 2017-06-22.
 */
public class GenericCast {
    public static final int SIZE = 10;

    public static void main(String[] args) {
        FixedSizeStack<String> strings = new FixedSizeStack<>(SIZE);
        for(String s : "A B C D E F G H I J".split(" "))
            strings.push(s);
        for (int i = 0; i < SIZE; i++) {
            String s = strings.pop();
            System.out.println(s + " ");
        }
    }
}

class FixedSizeStack<T> {
    private int index = 0;
//    private Object[] storage;
    private ArrayList<T> storage;
    public FixedSizeStack(int size) {
//        storage = new Object[size];
        storage = new ArrayList<>(size);
    }

    public void push(T item) {
//        storage[index++] = item;
        storage.set(index++, item);
    }

    public T pop() {
//        return (T) storage[--index];
        return storage.get(--index);
    }

}
