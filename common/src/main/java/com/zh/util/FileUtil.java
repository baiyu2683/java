package com.zh.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件操作相关
 *
 */
public class FileUtil {

    /**
     * 使用buffer进行文件复制，实际和普通io差别不大
     * 因为jdk中普通流已经用nio重新实现过了
     * @param from
     * @param to
     * @throws IOException
     */
    public static void copyNIO(File from, File to) throws IOException {
        FileInputStream fis = new FileInputStream(from);
        FileOutputStream fos = new FileOutputStream(to);

        FileChannel fisc = fis.getChannel();
        FileChannel fosc = fos.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(2048);

        while(true) {
            bb.clear(); // 使用之前首先清空缓冲区
            int count = fisc.read(bb);
            if (count < 0) break;
            bb.flip(); // position = 0，准备开始读数据
            fosc.write(bb);
        }
    }

    /**
     * 文件复制
     * @param from
     * @param to
     * @throws IOException
     */
    public static void copy(File from, File to) throws IOException {
        FileInputStream fis = new FileInputStream(from);
        FileOutputStream fos = new FileOutputStream(to);

        int length = 0;
        byte[] buffer = new byte[2048];
        while ((length = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, length);
        }
    }
}
