package com.zh;

import org.junit.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2016/12/18.
 */
public class TestDaemonThread {

    @Test
    public void test1() throws InterruptedException {
        Map<Integer, Thread> threadMap = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        TimeUnit.SECONDS.sleep(3); // 中断会抛出异常
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId() + " " + this + "-" + this.getClass().getName());
                }

            });
            thread.setDaemon(true);
            thread.start();
            threadMap.put(i, thread);

        }
//        TimeUnit.SECONDS.sleep(10);
        for (int i = 0; i < 10; i++) {
            threadMap.get(i).interrupt();
        }
    }
}
