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
        String fileName = "";
        try {
            fileName = MyTask.blockingQueue.take();
            System.out.println(fileName + "出队！");
        } catch (InterruptedException e) {
            System.out.println("获取失败!");
        }
        File file = new File(Consts.DEFAULT_DIR + fileName);
        if(file.exists()){
            if (pattern.matcher(file.getName()).find()) {
                file.delete();
//                System.out.println("删除:" + file.getName() + "-" + file.getAbsolutePath());
            }
        }
    }
}
