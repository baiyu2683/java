package com.zh.concurrent.sample;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by zhangheng on 2016/10/17.
 */
public class Consumer implements Runnable {

//    private static final String regExp = "^(\\w+).ok$";

    private static final Pattern pattern = Pattern.compile("^(\\w+).ok$");

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
//            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File file = new File("F:/tmp/");
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File file1 : files){
                System.out.println("当前文件:" + file1.getName() + "-" + file1.getAbsolutePath());
                if(pattern.matcher(file1.getName()).find()) {
                    file1.delete();
                    System.out.println("删除:" + file1.getName() + "-" + file1.getAbsolutePath());
                }
            }
        }
    }
}
