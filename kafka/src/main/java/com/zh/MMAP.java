package com.zh;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class MMAP {

    private static long FILE_LENGTH = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:/raf.log", "rw");
        MappedByteBuffer mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, FILE_LENGTH);
        mappedByteBuffer.clear();
        mappedByteBuffer.put((byte)1);
        mappedByteBuffer.put((byte)2);
        mappedByteBuffer.flip();
        byte b = mappedByteBuffer.get();
        System.out.println(b);
        b = mappedByteBuffer.get();
        System.out.println(b);
        mappedByteBuffer.limit(mappedByteBuffer.capacity());
        System.out.println(mappedByteBuffer.limit() + "-" + mappedByteBuffer.position());
        mappedByteBuffer.put((byte)3);
        System.out.println(mappedByteBuffer.limit() + "-" + mappedByteBuffer.position());
        mappedByteBuffer.position(2);
        b = mappedByteBuffer.get();
        System.out.println(b);
        randomAccessFile.close();
    }
}
