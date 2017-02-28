package com.zh.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 用文件通道复制文件
 * Created by zh on 2017-03-01.
 */
public class TransferTo {
    public static void main(String[] args) throws Exception {
        String inStr = "f:/testchannelin.txt";
        String outStr = "f:/testchannelout.txt";
        FileChannel in = new FileInputStream(inStr).getChannel(),
                out = new FileOutputStream(outStr, false).getChannel();
        for(int i = 0; i < 10; i++)
            in.transferTo(out.size(), in.size(), out);
    }
}
