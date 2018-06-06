package com.zh.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by zh on 2017-03-01.
 */
public class BufferToText {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception {
        FileChannel fileChannel = new FileOutputStream("F:/testout.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes()));
        fileChannel.close();
        fileChannel = new FileInputStream("f:/testin.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        //Does't work
        System.out.println(buffer.asCharBuffer());
        //Decode using this system's default Charset:
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoding using " + encoding + ": " + Charset.forName(encoding).decode(buffer));
        //Or, we could encode with something that will print:
        fileChannel = new FileOutputStream("f:/testout.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fileChannel.close();
        //Now try reading again:
        fileChannel = new FileInputStream("f:/testin.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        //Use a CharBuffer to write through:
        fileChannel = new FileOutputStream("f:/testout.txt").getChannel();
        buffer = ByteBuffer.allocate(24); //More than needed
        buffer.asCharBuffer().put("Some text");
        fileChannel.write(buffer);
        fileChannel.close();
        //Read and display
        fileChannel = new FileInputStream("f:/testin.txt").getChannel();
        buffer.clear();
        fileChannel.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
