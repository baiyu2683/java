package com.zh.concurrent.sample;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhangheng on 16-10-22.
 */
public class MyTask {
    /**
     * 生产的文件
     */
//    public static BlockingDeque<String> blockingQueue = new LinkedBlockingDeque<String>(3);
    public static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);
}
