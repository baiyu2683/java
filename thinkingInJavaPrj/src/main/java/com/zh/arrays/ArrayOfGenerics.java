package com.zh.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017-06-27.
 */
public class ArrayOfGenerics {
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[20];
        ls = (List<String>[])la; //unchecked warning
        ls[0] = new ArrayList<>();
//        ls[1] = new ArrayList<Integer>();

        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();
        List<Integer> s = (List<Integer>) objects[1];
        s.add(22);

        List<BerylliumSphere>[] spheres = (List<BerylliumSphere>[])new List[10];
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new ArrayList<>();
    }
}
