package com.zh;

import com.zh.util.FileUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 测试nio和普通io复制操作
 * 发现差别不大。。由于普通io在jdk中已经通过nio实现过了
 */
public class TestNIOAndIO {

    private static File from;
    private long start;
    private long end;
    
    @BeforeClass
    public static void beforeClass() throws IOException {
        from = new File("d:/dataJson.json");
        if (!from.exists()) from.createNewFile();
        FileWriter fileWriter = new FileWriter(from, true);
        try {
            if (from.length() < 10000) {
                for (int i = 0; i < 10000; i++) {
                    fileWriter.write("asdfasdfasdfasdfasdfasdfasdf\n");
                }
            }
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
            }
        }
    }

    @Before
    public void before() throws IOException {
        start = System.currentTimeMillis();
    }
    
    @After
    public void after() {
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void test2() throws IOException {
        System.out.println("test2");
        for (int i = 0; i < 100; i++) {
            FileUtil.copy(new File("d:/dataJson.json"), new File("d:/dataJsonBackupNIO.json"));
        }
    }
    
    @Test
    public void test1() throws IOException {
        System.out.println("test1");
        for (int i = 0; i < 100; i++) {
            FileUtil.copyNIO(new File("d:/dataJson.json"), new File("d:/dataJsonBackupNIO.json"));
        }
    }


}
