package com.zh.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Create a serialized output file.
 * Created by zh on 2017-03-14.
 */
public class FreezeAlien {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("f:/X.file")
        );
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
