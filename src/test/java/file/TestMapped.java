package file;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zh on 2017-09-10.
 */
public class TestMapped {

    @Test
    public void test1() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("f:/test1zhangheng");
        FileChannel fileChannel = fis.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        fis.close();

        File file = new File("f:/test2zhangheng");
        if(!file.exists()) file.createNewFile();
//        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel1 = accessFile.getChannel();
//        fileChannel1.write(mappedByteBuffer);
//        fileChannel1.close();
        MappedByteBuffer mappedByteBuffer1 = fileChannel1.map(FileChannel.MapMode.READ_WRITE, 0, mappedByteBuffer.capacity());
        mappedByteBuffer1.put(mappedByteBuffer);
        mappedByteBuffer1.force();
        accessFile.close();
        System.out.println("时间:" + (System.currentTimeMillis() - start));
    }
    @Test
    public void test4() throws IOException {
        long start = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10 * 1024 * 1024);
        FileInputStream fileInputStream = new FileInputStream("f:/test1zhangheng");
        File file = new File("f:/test2zhangheng");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
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
        System.out.println("test3:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("f:/test1zhangheng");
        File file = new File("f:/test2zhangheng");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[10 * 1024 * 1024];
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
    public void test3() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("f:/test1zhangheng");
        File file = new File("f:/test3zhangheng");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fin = fileInputStream.getChannel();
        FileChannel fou = fileOutputStream.getChannel();
        fin.transferTo(0, fin.size(), fou);
        fin.close();
        fou.close();
        System.out.println("test3:" + (System.currentTimeMillis() - start));
    }

    /**
     * 新建一个空文件
     * @throws IOException
     */
    @Test
    public void test5() throws IOException {
        File file = new File("f:/testcreate1");
//        if(!file.exists()) file.createNewFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.setLength(2_048);
    }
    @Test
    public void test6() throws IOException {
        File file = new File("f:/testcreate1");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file, true);
        FileChannel fileChannel = fos.getChannel();
    }
}
