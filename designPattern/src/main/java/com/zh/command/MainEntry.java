package com.zh.command;

import com.zh.command.command.Command;
import com.zh.command.command.FireFightingCommand;
import com.zh.command.command.ShowCommandImpl;
import com.zh.command.receiver.FiremanReceiver;
import com.zh.command.receiver.PrinterReceiver;

/**
 * 入口测试类
 */
public class MainEntry {

    public static void main(String[] args) {
        Command command = new FireFightingCommand(new FiremanReceiver());
        Command command1 = new ShowCommandImpl(new PrinterReceiver());

        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.doSomethind();

        invoker.setCommand(command1);
        invoker.doSomethind();
    }
}
