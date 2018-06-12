package com.zh.command.command;

import com.zh.command.receiver.Receiver;

/**
 *  打印命令
 */
public class ShowCommandImpl implements Command {

    private Receiver receiver;

    public ShowCommandImpl(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }
}
