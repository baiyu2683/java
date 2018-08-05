package com.zh.nio;


import java.nio.ByteBuffer;

/**
 * 将ByteBuffer转换为字节数组
 * @Author zh2683
 */
public class ByteBufferTest {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.wrap("123".getBytes());
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes, 0, bytes.length);
        System.out.println(new String(bytes));
    }
}
