package com.zh;

import org.junit.Test;

/**
 * Created by zh on 2017-07-20.
 */
public class TestShutDownHook {

    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(1);
        });
        Runtime.getRuntime().addShutdownHook(thread);
        System.exit(0);
//        List<String> list = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        for(;;) {
//            sb.append(UUID.randomUUID().toString());
//            list.add(sb.toString());
//        }
    }
}
