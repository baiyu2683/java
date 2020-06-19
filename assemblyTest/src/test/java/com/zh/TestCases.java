package com.zh;

import org.junit.Test;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCases {

    @Test
    public void getMacAddress() throws UnknownHostException, SocketException {
        System.out.println(2 / 3f);
        System.out.println(Math.round(2 / 5f));
    }

    @Test
    public void bigDecimal() {
        BigDecimal bd = new BigDecimal(127.1123123d);
        System.out.println(bd.floatValue());
    }

    @Test
    public void insertWhiteSpace() {
        System.out.println("宋体".replace("", " ").trim());
    }

    @Test
    public void replaceTest() {
        String s = "";
        System.out.println(s);
        s = s.replace("<br>", "<br/>");
        Pattern pattern = Pattern.compile("(<img[^>]*?)>");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
        }
        s = s.replaceAll("(<img[^>]*?)>", "$1/>");
        System.out.println(s);
    }

    @Test
    public void intConverter() {
        Double d = Double.valueOf("12.511");
        System.out.println((int)Math.round(d));
    }

    @Test
    public void splitTest() {
        String tagReg = "<(\\w*?) {0,}(.*?)>([\\w\\W]*?)</\\1>";
        String content = "font里 \n" +
                "<strike>\n" +
                "  asdf \n" +
                " <b> <u>混合1</u> asdf </b> \n" +
                "</strike>";
        String[] ss = content.split(tagReg);
        System.out.println(ss);
        Pattern pattern = Pattern.compile(tagReg);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            int groupCount = matcher.groupCount();
            System.out.println(matcher.group(0));
            if (groupCount > 0) {
                System.out.println("1:" + matcher.group(1));
            }
            if (groupCount > 1) {
                System.out.println("2:" + matcher.group(2));
            }
            if (groupCount > 2) {
                System.out.println("3:" + matcher.group(3));
            }
        }
    }

    private volatile int count;

    private static AtomicIntegerFieldUpdater<TestCases> fieldUpdater
            = AtomicIntegerFieldUpdater.newUpdater(TestCases.class, "count");

    private void incrementCount() {
        fieldUpdater.addAndGet(this, 1);
//        count++;
    }

    @Test
    public void testAtomicFieldUpdater() {
        TestCases testCases = new TestCases();
        int n = 5000;
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0 ; i < n ; i++) {
            new Thread(() -> {
                Thread.yield();
                testCases.incrementCount();
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
            System.out.println(testCases.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(ReentrantReadWriteLock reentrantReadWriteLock) {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        try {
            writeLock.lock();
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int read(ReentrantReadWriteLock reentrantReadWriteLock) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        try {
            readLock.lock();
            return count;
        } finally {
            readLock.unlock();
        }
    }

    @Test
    public void testReentrantLock() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    }

    @Test
    public void testThreeScale() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 2;
        double x = endTime - startTime;
        System.out.println(String.format("导出成功! 共耗时：%s秒。", x/1000));
    }

    @Test
    public void testFont() {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0 ; i < 10 ; i++) {
            new Thread(() -> {
                Font font = new Font("微软雅黑", Font.PLAIN, 20);
                Thread.yield();
                long start = System.currentTimeMillis();
                FontDesignMetrics.getMetrics(font);
                System.out.println(System.currentTimeMillis() - start);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
