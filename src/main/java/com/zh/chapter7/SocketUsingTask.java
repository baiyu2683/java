package com.zh.chapter7;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * Created by zh on 2017-09-21.
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {

    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    public synchronized void cancel() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {}
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
          public boolean cancel(boolean mayInterruptIfRunning) {
              try {
                  SocketUsingTask.this.cancel();
              } finally {
                  return super.cancel(mayInterruptIfRunning);
              }
          }
        };
    }
}
