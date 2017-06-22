package com.zh.generics;

import com.zh.generic.Shape;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by zh on 2017-06-22.
 */
public class NeedCasting {
    public void f(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(args[0])
        );
        List<Shape> shapes = (List<Shape>) in.readObject();
    }
}
