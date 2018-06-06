package com.zh.chapter7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by zh on 2017-09-21.
 */
public class TestReadThread {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(10010), 10000);
//        FutureTask<?> task = (FutureTask<?>) es.submit(new ReadThread(socket));
//        try {
//            task.get(3, TimeUnit.SECONDS);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        } finally {
//            task.cancel(true);
//        }
//        System.out.println(1);
        Thread thread = new ReadThread(socket);
        thread.start();
        TimeUnit.SECONDS.sleep(4);
        thread.interrupt();
    }
}
