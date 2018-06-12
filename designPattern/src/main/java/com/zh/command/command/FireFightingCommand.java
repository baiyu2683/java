package com.zh.command.command;

import com.zh.command.receiver.Receiver;

/**
 * 救火命令
 */
public class FireFightingCommand implements Command {

    private Receiver receiver;

    public FireFightingCommand(Receiver receiver) {
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
