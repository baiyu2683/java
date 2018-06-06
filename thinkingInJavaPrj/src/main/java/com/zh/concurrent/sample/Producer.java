package com.zh.concurrent.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhangheng on 2016/10/17.
 */
public class Producer implements Runnable{

    public void run() {
        String fileName = new Random().nextInt(Integer.MAX_VALUE) + ".ok";
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File dir = new File(Consts.DEFAULT_DIR);
        synchronized(dir) {
            if (!dir.exists()) {
                dir.mkdir();
            }
        }
        File file = new File(Consts.DEFAULT_DIR + fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
            } finally {
//                System.out.println("文件写入:" + fileName);
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(String.valueOf(fileName).getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            System.out.println(fileName + "入队!");
            MyTask.blockingQueue.put(fileName);
        } catch (InterruptedException e) {
            System.out.println("放入队列失败!");
        }
    }
}
