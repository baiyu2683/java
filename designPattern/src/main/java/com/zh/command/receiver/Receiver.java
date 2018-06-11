package com.zh.command.receiver;

/**
 * 接受者
 * 调用者和接收者 通过命令来进行解耦
 */
public interface Receiver {
    void doSomething();
}