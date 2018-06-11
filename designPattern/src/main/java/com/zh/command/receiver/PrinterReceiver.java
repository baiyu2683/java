package com.zh.command.receiver;

/**
 * 打印机
 */
public class PrinterReceiver implements Receiver {

    @Override
    public void doSomething() {
        System.out.println("打印。。。");
    }
}
