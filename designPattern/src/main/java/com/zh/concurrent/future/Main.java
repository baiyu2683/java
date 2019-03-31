package com.zh.concurrent.future;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main BEGIN");
        Host host = new Host();
        FutureDataListener listener = futureData -> {
            System.out.println("listener: " + futureData.getContent());
        };
        Data data1 = host.request(10, 'A', listener);
        Data data2 = host.request(20, 'B', listener);
        Data data3 = host.request(30, 'C', listener);
        System.out.println("main otherJob BEGIN");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
        System.out.println("main otherJob END");
//        System.out.println("data1 = " + data1.getContent());
//        System.out.println("data2 = " + data2.getContent());
//        System.out.println("data3 = " + data3.getContent());
        System.out.println("main END");
        new CountDownLatch(1).await();
    }
}
