package com.zh.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 文件锁
 * Created by zh on 2017-03-12.
 */
public class FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
//        FileOutputStream fos = new FileOutputStream("f:/file.txt");
//        FileLock fileLock = fos.getChannel().tryLock();
//        if (fileLock != null) {
//            System.out.println("Locked file");
//            TimeUnit.MILLISECONDS.sleep(100);
//            fileLock.release();
//            System.out.println("Released Lock");
//        }
//        fos.close();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1; i++)
            executorService.submit(new TestFileLock());
    }

    public static class TestFileLock implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("f:/file.txt");
                    long start = System.currentTimeMillis();
                    FileLock fileLock = fos.getChannel().lock();
                    System.out.println(System.currentTimeMillis() - start);
                    if (fileLock != null) {
                        System.out.println(Thread.currentThread() + "Locked file");
                        TimeUnit.MILLISECONDS.sleep(5000);
                        fileLock.release();
                        System.out.println(Thread.currentThread() + "Released Lock");
                    }
                    fos.close();
                } catch (Exception e) {
                    System.out.println("----" + Thread.currentThread() + "----");
                } finally {
                    if (fos != null)
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }
}
