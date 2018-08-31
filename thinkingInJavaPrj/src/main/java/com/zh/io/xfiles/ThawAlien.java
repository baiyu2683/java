package com.zh.io.xfiles;

import java.io.*;

import com.zh.io.Alien;

/**
 * recover a serialized file whithout the class of object that's stored in that file.
 * Created by zh on 2017-03-14.
 */
public class ThawAlien {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Alien alien = new Alien();
        ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream("e:/X.file")
        );
        output.writeObject(alien);
        output.close();
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File("e:/X.file"))
        );
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
        System.out.println(mystery);
        // 第二次地址就不同了
        in = new ObjectInputStream(
                new FileInputStream(new File("e:/X.file"))
        );
        mystery = in.readObject();
        System.out.println(mystery.getClass());
        System.out.println(mystery);
    }
    
}
