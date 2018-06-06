package com.zh.io;

import java.io.*;

/**
 * DataOutputStream 和 DataInputStream
 * 保证读和写的内容是一致的
 * Created by zh on 2017-02-26.
 */
public class StoringAndRecoveringData {

    public static void main(String[] args) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("f:/test.txt"))
        );
        outputStream.writeDouble(3.141592653589793);
        outputStream.writeUTF("张恒");
        outputStream.writeDouble(1.414);
        outputStream.writeUTF("Square root of 2");
        outputStream.close();
        DataInputStream dataInputStream = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("f:/test.txt")
                )
        );
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readDouble());
        System.out.println(dataInputStream.readUTF());
    }
}
