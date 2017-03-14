package com.zh.io.xfiles;

import java.io.*;

/**
 * recover a serialized file whithout the class of object that's stored in that file.
 * Created by zh on 2017-03-14.
 */
public class ThawAlien {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File("f:/X.file"))
        );
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
    }
}
