package com.zh.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zh on 2017-02-28.
 */
public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        //Write a file;
        FileChannel fileChannel = new FileOutputStream("f:/testbytebuffer.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text ".getBytes()));
        fileChannel.close();
        //add to the end of the file
        fileChannel = new RandomAccessFile("f:/testbytebuffer.txt", "rw").getChannel();
        fileChannel.position(fileChannel.size()); //move to the end
        fileChannel.write(ByteBuffer.wrap("Some more".getBytes()));
        fileChannel.close();
        //Read the file
        fileChannel = new FileInputStream("f:/testbytebuffer.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        //读取之前reset
        buffer.flip();
        while (buffer.hasRemaining())
            System.out.println((char)buffer.get());
    }
}
