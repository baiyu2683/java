package com.zh.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件，将不能放到内存的大文件映射到内存，可以像数组一样操作
 * Created by zh on 2017-03-12.
 */
public class LargeMappedFiles {
    static int length = 0x8FFFFFF;   //128MB
    public static void main(String[] args) throws IOException {
        MappedByteBuffer out = new RandomAccessFile("f:/test.dat", "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++)
            out.put((byte)'x');
        System.out.println("Finished writing");
        for (int i = length/2; i < length/2 + 6; i++)
            System.out.print((char)out.get(i));
    }
}
