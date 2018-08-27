package com.zh.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zh2683
 */
public class SelectSockets {

    public static int PORT_NUMBER = 1234;

    private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        if (args == null || args.length <= 0) {
            args = new String[]{String.valueOf(PORT_NUMBER)};
        }
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(count.getAndSet(0));
        },0, 1, TimeUnit.SECONDS);
        new SelectSockets().go(args);
    }

    public void go(String[] args) throws Exception {
        int port = PORT_NUMBER;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.println("Listening on port " + port);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        Selector selector = Selector.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int n = selector.select(); // 这里会阻塞， 需要wakeup?
            if (n == 0) continue;
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 请求数+1
                    count.getAndIncrement();

                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    // 注册读操作
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    sayHello(channel);
                }
                // 读数据
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                iterator.remove(); // 已经被处理的key需要手动移除
            }

        }
    }

    protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws Exception {

        if (channel == null) return;

        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    private void sayHello(SocketChannel channel) throws Exception {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        byte[] result = new byte[1024];
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear();
        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip();

//            while (buffer.hasRemaining()) {
//                socketChannel.write(buffer);
//            }
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            result = concat(bytes, result);

            buffer.clear();
        }

        if (count <= 0) {
//            System.out.println(new String(result, "utf-8"));
//            System.out.println("完结...");
            socketChannel.close();
        }
    }

    // 合并两个数组
    public byte[] concat(byte[] src, byte[] dest) {
        byte[] result = Arrays.copyOf(src, src.length + dest.length);
        System.arraycopy(dest, 0, result, src.length, dest.length);
        return result;
    }
}
