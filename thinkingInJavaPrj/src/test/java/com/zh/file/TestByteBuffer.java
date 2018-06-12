package com.zh.file;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zh on 2017-09-10.
 */
public class TestByteBuffer {

    @Test
    public void test1() throws IOException {
        long start = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        FileInputStream fileInputStream = new FileInputStream("f:/zhangheng");
        File file = new File("f:/zhanghengbackup1");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream("f:/zhanghengbackup1");
        FileChannel in = fileInputStream.getChannel();
        FileChannel out = fileOutputStream.getChannel();
        byteBuffer.clear();
        while (in.read(byteBuffer) > 0 || byteBuffer.position() != 0) {
            byteBuffer.flip();
            out.write(byteBuffer);
            byteBuffer.compact();
        }
        fileInputStream.close();
        fileOutputStream.close();
        System.out.println("test1:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("f:/zhangheng");
        File file = new File("f:/zhanghengbackup2");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream("f:/zhanghengbackup2");
        byte[] buffer = new byte[10 * 1024];
        int index = 0;
        while ((index = fileInputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, index);
        }
        fileOutputStream.flush();
        fileInputStream.close();
        fileOutputStream.close();
        System.out.println("test2:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test3() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10 * 1024);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        charBuffer.put('a');
        charBuffer.flip();
        System.out.println(charBuffer.get());
    }
}
