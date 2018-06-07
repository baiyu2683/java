package com.zh;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存OOM
 *
 * VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @Author zh2683
 */
public class DirectMemoryOOM {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int i = 0;
        while (true) {
            unsafe.allocateMemory(_1M);
            System.out.println(++i);
        }
    }
}
