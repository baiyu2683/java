package com.zh.arrays;

import com.sun.jndi.ldap.Ber;

import java.util.Arrays;

/**
 * Created by zhangheng on 2017/6/27.
 */
public class ArrayInitTest {
    public static void main(String[] args) {
        BerylliumSphere[] berylliumSpheres = new BerylliumSphere[10];
        BerylliumSphere[] berylliumSpheres1 = {new BerylliumSphere(), new BerylliumSphere()};
        show(berylliumSpheres);
        show(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()});
        System.out.println(Arrays.deepToString(berylliumSpheres1));
    }

    private static void show(BerylliumSphere[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
