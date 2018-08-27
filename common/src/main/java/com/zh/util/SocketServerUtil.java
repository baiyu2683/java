package com.zh.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/**
 *
 */
public class SocketServerUtil {
    private static ByteBuffer buffer = ByteBuffer.allocate(2048);
    private static Integer DEFAULT_PORT = 1234;
    public static void start(int port, Consumer<Integer> callback, Consumer<String> handler) throws IOException {
        Selector selector = null;
        try {
            selector = Selector.open();
            // 开启一个通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            callback.accept(port);
        } catch (Exception e) {
            throw e;
        }
        while (true) {
            // 返回0说明没有需要处理的channel
            int n = selector.select();
            if (n == 0) continue;

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) { // 只有ServerSocketChannel才会注册这个事件
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel sc = ssc.accept();

                    // 注册一个读的channel
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }

                if (selectionKey.isReadable()) {
                    SocketChannel sc = (SocketChannel) selectionKey.channel();

                    buffer.clear();
                    int count = 0;
                    byte[] content = new byte[0];
                    while ((count = sc.read(buffer)) >= 0) {
                        byte[] read = new byte[count];
                        buffer.flip();
                        buffer.get(read);
                        // 加入content中
                        int contentLength = content.length;
                        content = Arrays.copyOf(content, contentLength + count);
                        System.arraycopy(read, 0, content, contentLength, count);
                        buffer.clear();
                        System.out.println("count: " + count);
                        // 处理数据
                        handler.accept(new String(content));
                    }

                }
                // 处理完成需要删除，不会自动删除
                iterator.remove();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        SocketServerUtil.start(DEFAULT_PORT, t -> {
            System.out.println("服务启动成功! 端口号:" + t);
        }, t -> {
            System.out.println("接收数据: " + t);
            System.out.println("==========================");
        });
    }
}
