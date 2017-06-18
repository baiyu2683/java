package com.zh.generics;

import java.util.Arrays;

/**
 * 泛型，由于擦除的存在，泛型数组不能直接转换为指定类型，必须为object，否则会出现classcastexception
 * Created by zh on 2017-05-25.
 */
public class GenericArray<T> {
    private T[] array;

    public GenericArray(int sz) {
        array = (T[]) new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {return array[index];}

    public T[] rep() {return array;}

    public static void main(String[] args) {
        GenericArray<Integer> gai = new GenericArray<>(10);
        Integer[] a = gai.rep(); //java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
        Object[] b = gai.rep();
        gai.put(0, 10);
        gai.put(1, 11);
        System.out.println(Arrays.toString(b));
    }
}
