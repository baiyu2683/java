package com.zh.net;

import org.apache.log4j.net.SocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Selector
 * 管理多个通道，通道必须非阻塞，所以不能管理FileChannel
 */
public class SelectorTest {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(1090));
        // 通道必须时非阻塞的
        serverSocketChannel.configureBlocking(false);

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int readycount = selector.select();
            if (readycount == 0) continue;
            System.out.println("就绪...");
            SelectionKey selectionKey1 = selector.selectedKeys().iterator().next();
            if (selectionKey1.isConnectable()) {
                System.out.println("connectable");
            }
            if (selectionKey1.isReadable()) {
                System.out.println("readable");
            }
            if (selectionKey1.isWritable()) {
                System.out.println("writable");
            }
            if (selectionKey1.isAcceptable()) {
                System.out.println("acceptable");
                ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey1.channel();
                SocketChannel socketChannel = serverSocketChannel1.accept();
                ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                byteBuffer.asIntBuffer().put(1);
                socketChannel.write(byteBuffer);
            }
//            if (selectionKey.isValid()) {
//                System.out.println("valid");
//            }
        }

    }
}
