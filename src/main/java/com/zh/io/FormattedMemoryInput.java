package com.zh.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by zh on 2017-02-26.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            //读取字节
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read(Class.forName(FormattedMemoryInput.class.getName()).getClassLoader().getResource("testRead.json").getFile()).getBytes()
                    )
            );
            //读取的是字节，任何值都是合法的，所以不能用返回值确定是否结束
//            while(true)
//                System.out.println((char)in.readByte());
            //available()可以返回还剩多少字节
            while(in.available() != 0)
                System.out.println((char)in.readByte());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("End of Stream");
        }
    }
}
