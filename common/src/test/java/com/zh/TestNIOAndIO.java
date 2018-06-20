package com.zh;

import com.zh.util.FileUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 测试nio和普通io复制操作
 * 发现差别不大。。由于普通io在jdk中已经通过nio实现过了
 */
public class TestNIOAndIO {

    private File from;
    private File to;

    @Before
    public void before() throws IOException {
        from = new File("d:/dataJson.json");
        if (!from.exists()) from.createNewFile();
        if (from.length() < 10000) {
            FileWriter fileWriter = new FileWriter(from, true);
            for (int i = 0; i < 10000; i++) {
                fileWriter.write("asdfasdfasdfasdfasdfasdfasdf\n");
            }
        }
    }

    @Test
    public void test2() throws IOException {
        for (int i = 0; i < 100; i++) {
            FileUtil.copy(new File("d:/dataJson.json"), new File("d:/dataJsonBackupNIO.json"));
        }
    }
    @Test
    public void test1() throws IOException {
        for (int i = 0; i < 100; i++) {
            FileUtil.copyNIO(new File("d:/dataJson.json"), new File("d:/dataJsonBackupNIO.json"));
        }
    }


}
