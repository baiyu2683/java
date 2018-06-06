package com.zh.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

/**
 * RandomAccessFile
 * Created by zh on 2017-02-26.
 */
public class UsingRandomAccessFile {

    static String file = "f:/rtest.data";
    static void display() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        for(int i = 0; i < 7; i++)
            System.out.println("value " + i + ":" + randomAccessFile.readDouble());
        System.out.println(randomAccessFile.readUTF());
        randomAccessFile.close();
    }

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        for(int i = 0; i < 7; i++)
            randomAccessFile.writeDouble(i * 1.414);
        randomAccessFile.writeUTF("The end of the file");
        randomAccessFile.close();
        display();
        randomAccessFile = new RandomAccessFile(file, "rw");
        //double8字节长
        randomAccessFile.seek(5*8);
        randomAccessFile.writeDouble(47.001);
        randomAccessFile.close();
        display();
    }
}
