package com.zh.chapter7;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by zh on 2017-09-21.
 */
public class TestCancellingExecutor {

    public static void main(String[] args) throws IOException, InterruptedException {
        CancellingExecutor executor = new CancellingExecutor(1,1, 10, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1));
        SocketUsingTask<String> socketUsingTask = new SocketUsingTask<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        };
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(10010));
        socketUsingTask.setSocket(socket);
        RunnableFuture rf = executor.newTaskFor(socketUsingTask);
        TimeUnit.SECONDS.sleep(4);
        rf.cancel(true);
    }
}
