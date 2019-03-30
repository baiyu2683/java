package com.zh;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程通过建同名文件实现同步
 * @see com.zh.io.FileLocking
 */
public class TestFileLock {

    @Test
    public void test1() throws InterruptedException, IOException {
        // 删除锁文件
        File lockFile = new File("/home/zh/test.txt");
        if (lockFile.exists()) {
            lockFile.delete();
        }
        //
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        for (int i = 0 ; i < 10 ; i++) {
            final int tmp = i;
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
                File file = new File("/home/zh/test.txt");
                boolean flag = false;
                try {
                    if (!file.createNewFile()) {
//                        System.out.println("加锁失败, " + Thread.currentThread().getName());
                    } else {
                        flag = true;
                        // jvm退出时删除文件
                        file.deleteOnExit();
                        System.out.println("加锁成功, " + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    file.deleteOnExit();
                    if (flag) {
                        System.out.println("解锁, " + Thread.currentThread().getName());
                        // 立即删除文件
                        file.delete();
                    }
                }
            }, 2, 5, TimeUnit.SECONDS);
        }
        System.in.read();
    }
}
