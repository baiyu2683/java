package com.zh.chapter5;

import java.util.concurrent.FutureTask;

public class FutureTaskMain {

    public static void main(String[] args) {
        FutureTask<String> taskMain = new FutureTask(() -> {
            return "10";
        });
    }
}
