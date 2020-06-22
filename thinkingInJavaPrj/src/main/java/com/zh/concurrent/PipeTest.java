package com.zh.concurrent;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.locks.LockSupport;

public class PipeTest {

    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();
            new Thread(() -> {
                Pipe.SinkChannel sink = pipe.sink();
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                    for (int i = 0 ; i < 20 ; i++) {
                        byteBuffer.clear();
                        byteBuffer.putInt(i);
                        byteBuffer.flip();
                        sink.write(byteBuffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                Pipe.SourceChannel source = pipe.source();
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                    int count = 0;
                    while (true) {
                        // 写模式
                        byteBuffer.clear();
                        source.read(byteBuffer);
                        // 读模式
                        byteBuffer.flip();
                        stringBuilder.append(byteBuffer.getInt());
                        System.out.println(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LockSupport.park();
    }
}
