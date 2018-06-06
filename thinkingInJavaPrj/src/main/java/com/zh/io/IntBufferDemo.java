package com.zh.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Created by zh on 2017-03-12.
 */
public class IntBufferDemo {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
//        IntBuffer intBuffer = buffer.asIntBuffer();
        LongBuffer longBuffer = buffer.asLongBuffer();
        //Store an array of int:
//        intBuffer.put(new int[]{11, 23, 34, 123});
        longBuffer.put(new long[]{1,2,3,5,123,8976712});
        System.out.println(longBuffer.get(3));
        longBuffer.put(3, 345);

//        intBuffer.rewind();
//        System.out.println(intBuffer.get());
        longBuffer.flip();   //置limit为当前position，即所有内容的最终位置
        while (longBuffer.hasRemaining()) {  //hasRemaining利用limit判断是否结束
            long i = longBuffer.get();
            System.out.println(i);
        }
    }
}
