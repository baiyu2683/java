package com.zh.command;

import com.zh.command.command.Command;
import com.zh.command.command.FireFightingCommand;
import com.zh.command.command.ShowCommandImpl;
import com.zh.command.receiver.FiremanReceiver;
import com.zh.command.receiver.PrinterReceiver;

/**
 * 状态模式使用场景:
 * 1, 事务，在command中实现
 * 2, 多级回退，使用栈保存每个执行的命令，回退时出栈回退
 * 3，java线程池中，每个ExecutorService(调用者)执行一个Runnable（命令），Runnable中执行不同的任务
 */
public class MainEntry {

    public static void main(String[] args) {
        Command command = new FireFightingCommand(new FiremanReceiver());
        Command command1 = new ShowCommandImpl(new PrinterReceiver());

        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.doSomething();

        invoker.setCommand(command1);
        invoker.doSomething();

        command1.setReceiver(new FiremanReceiver());
        invoker.doSomething();
    }
}
