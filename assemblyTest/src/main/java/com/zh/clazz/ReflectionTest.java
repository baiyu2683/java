package com.zh.clazz;

import java.lang.reflect.InvocationTargetException;

public class ReflectionTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MyClassLoader myClassLoader = MyClassLoader.class.getConstructor().newInstance();
        System.out.printf("hh");
    }
}
