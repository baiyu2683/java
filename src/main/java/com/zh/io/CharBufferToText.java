package com.zh.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zh on 2017-03-12.
 */
public class CharBufferToText {
    public static void main(String[] args) throws FileNotFoundException {
        CharBuffer charBuffer = CharBuffer.allocate(2048);
        charBuffer.wrap("Some text".toCharArray());
        FileChannel fileChannel = new FileOutputStream("F:/charBuffer.txt").getChannel();
    }
}
