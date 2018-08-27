package com.zh.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketUtil {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(1234));
        OutputStream os = socket.getOutputStream();
        for (int count = 0 ; count < 10 ; count++) {
            for (int i = 0; i < 4; i++) {
                os.write(String.valueOf(i).getBytes());
            }
            os.flush();
        }
        TimeUnit.SECONDS.sleep(100);
    }

    public static void main1(String[] args) throws IOException, InterruptedException {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1", 1234));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            for (int count = 0 ; count < 10 ; count++) {
                for (int i = 0; i < 4; i++) {
                    String str = String.valueOf(i);
                    System.out.println(str);
                    buffer.clear();
                    buffer.put(str.getBytes()).flip();
                    sc.write(buffer);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
