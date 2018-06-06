package com.zh.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 通过改变byteBuffer字节存储书序改变输出
 * Created by zh on 2017-03-12.
 */
public class Endians {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[13]);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
        buffer.rewind();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
        buffer.rewind();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
    }
}
