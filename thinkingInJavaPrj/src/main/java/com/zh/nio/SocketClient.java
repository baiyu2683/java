package com.zh.nio;

import com.zh.concurrent.AtomicIntegerTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zh2683
 */
public class SocketClient {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        for (int i = 0 ; i < 8 ; i++) {
            threadPoolExecutor.execute(new Thread(() -> {
                OutputStream outputStream = null;
                while (true) {
                    try {
                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress(1234));
                        outputStream = socket.getOutputStream();
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[102400];
                        inputStream.read(bytes);
                        System.out.printf(new String(bytes));
                        String msg = "张恒" + atomicInteger.incrementAndGet() + "\n";
                        outputStream.write(msg.getBytes("utf-8"));
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }));
        }
    }



}
