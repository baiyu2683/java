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
 * @see com.zh.TestFileLock
 */
public class FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++)
            executorService.submit(new TestFileLock());
    }

    public static class TestFileLock implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("/home/zh/file.txt");
                    long start = System.currentTimeMillis();
                    FileLock fileLock = fos.getChannel().lock();
                    System.out.println(System.currentTimeMillis() - start);
                    if (fileLock != null) {
                        System.out.println(Thread.currentThread() + "Locked file");
                        TimeUnit.SECONDS.sleep(5);
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
