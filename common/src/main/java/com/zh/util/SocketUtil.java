package com.zh.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketUtil {

    public static void main(String[] args) {
        for (int i = 0 ; i < 10 ; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(1234));
                    OutputStream os = socket.getOutputStream();
                    for (int count = 0; count < 10; count++) {
                        for (int j = 0; j < 4; j++) {
                            os.write(String.valueOf(j).getBytes());
                        }
                        TimeUnit.SECONDS.sleep(1);
                    }
                    os.write(Thread.currentThread().getName().getBytes());
//                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
