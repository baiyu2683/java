package com.zh.concurrent.sample;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by zhangheng on 2016/10/17.
 */
public class MyMain {

    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }
}
