package com.zh.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 复制文件
 * Created by zh on 2017-03-01.
 */
public class ChannelCopy {

    private static final int BSIZE = 1024; //字节

    public static void  main(String[] args) throws Exception {
//        if(args.length != 2) {
//            System.out.println("arguments: sourcefile destfile");
//            System.exit(1);
//        }
        String inStr = "f:/testchannelin.txt";
        String outStr = "f:/testchannelout.txt";
        FileChannel in = new FileInputStream(inStr).getChannel(),
                out = new FileOutputStream(outStr).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        int count = 0;
        while(in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
            count++;
        }
        System.out.println(count);
    }
}
