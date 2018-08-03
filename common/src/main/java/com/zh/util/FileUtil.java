package com.zh.util;

import com.zh.constant.Consts;

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

    /**
     * 将文件读取为一个字符串
     * @param input
     * @return
     */
    public static String toString(File input, String charsetName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(input);
            FileChannel fisc = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            while (true) {
                byteBuffer.clear();
                int count = fisc.read(byteBuffer);
                if (count < 0) break;
                output.write(byteBuffer.array(), 0, count);
            }
            return output.toString(charsetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(File input) {
        return toString(input, "utf-8");
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\projects\\HappyRptServlet\\pom.xml");
        String s = toString(file, "gb2312");
        System.out.println(s);
    }
}
