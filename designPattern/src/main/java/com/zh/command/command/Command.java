package com.zh.command.command;

import com.zh.command.receiver.Receiver;

/**
 * 命令
 */
public interface Command {

    void setReceiver(Receiver receiver);

    void execute();

    void beginTransaction();
    void commit();
    void rollBack();
}
