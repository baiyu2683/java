package com.zh.io;

import java.nio.ByteBuffer;

/**
 * Created by zh on 2017-03-12.
 */
public class GetData {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while (i++ < buffer.limit())
            if(buffer.get() != 0)
                System.out.println("nonezero");
        System.out.println("i = " + i);
        buffer.rewind();
        //Store and read a char array
        buffer.asCharBuffer().put("Howdy!");
        char c;
        while ((c = buffer.getChar()) != 0)
            System.out.printf(c + " ");
        System.out.println();
        buffer.rewind();
        //Store and read a short
        buffer.asShortBuffer().put((short)471142);
        System.out.println(buffer.getShort());

        buffer.rewind();
        buffer.asLongBuffer().put(99471142);
        System.out.println(buffer.getLong());

        buffer.rewind();
        buffer.asFloatBuffer().put(99471142);
        System.out.println(buffer.getFloat());

        buffer.rewind();
        buffer.asDoubleBuffer().put(99471142);
        System.out.println(buffer.getDouble());
        buffer.rewind();
    }
}
