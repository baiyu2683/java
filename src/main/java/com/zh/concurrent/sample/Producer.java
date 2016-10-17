package com.zh.concurrent.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhangheng on 2016/10/17.
 */
public class Producer implements Runnable{

    private Integer num;

    public Producer(Integer num){
        this.num = num;
    }

    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File file = new File("F:/tmp/" + this.num + ".ok");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
            } finally {
                System.out.println("文件写入:" + this.num + ".ok");
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(String.valueOf(this.num).getBytes());
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
        System.out.println(Thread.currentThread().getName() + ": " + this.num);
    }
}
