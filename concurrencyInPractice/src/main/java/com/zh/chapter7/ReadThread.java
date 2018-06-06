package com.zh.chapter7;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-09-21.
 */
public class ReadThread extends Thread {

    private final Socket socket;
    private final InputStream in;

    public ReadThread(Socket socket) throws IOException {
        this.socket = socket;
        in = socket.getInputStream();
    }

    /**
     * 实现一个中断方法，用于中断线程并且关闭当前线程中的socket
     * 需要显示调用此方法，不会自动调用
     */
    @Override
    public void interrupt() {
        System.out.println("interrupt," + Thread.currentThread().getName());
        try {
            socket.close(); //1. socket
        } catch (IOException e) {
        } finally {
            super.interrupt();  //2. 中断线程
        }
    }

    public void run() {
        byte[] buf = new byte[1024];
        try {
            while (true) {
                int count = 0;
                count = in.read(buf);
                if (count < 0) break;
                else if (count > 0) processBuffer(buf, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processBuffer(byte[] buf, int count) {
        System.out.println("read count:" + count);
    }
}
