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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * 当OP_READ时间触发，int readByteNum = channel.read(buffer)会返回读到的字节数
 * 1. 当readByteNum > 0, 表示从channel中读取到了readByteNum个字节到buffer中
 * 2. 当readByteNum = 0, 表示channel中已经没有数据可以读取了，这个时候buffer的position==limit
 * 3. 当readByteNum==-1，表示远端channel正常关闭了。这个时候我们就需要进行钙通道的关闭和注销操作了
 */
public class SocketServerUtil {

    private static Integer DEFAULT_PORT = 1234;

    private static AtomicInteger counter = new AtomicInteger(0);

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
                    // 取消读标记,本地读完之后再开启
                    selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_READ));
                    // 异步读取,否则不能在一个循环内读完。。
                    new Thread(() -> {
                        try {
                            SocketChannel sc = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(2048);
                            buffer.clear();
                            int count = 0;
                            byte[] content = new byte[0];
                            while ((count = sc.read(buffer)) > 0) {
                                System.out.println("counter: " + counter.getAndSet(0));
                                byte[] read = new byte[count];
                                buffer.flip();
                                buffer.get(read);
                                // 加入content中
                                int contentLength = content.length;
                                content = Arrays.copyOf(content, contentLength + count);
                                System.arraycopy(read, 0, content, contentLength, count);
                                buffer.clear();
                            }
                            counter.incrementAndGet();

                            // 读到-1说明远端channel关闭了，进行该通道的关闭和注销操作
                            if (count == -1) {
                                System.out.println(count + "-" + counter.get());
                                selectionKey.channel().close();
                                return;
                            }

                            System.out.println("读完了.." + count);
                            System.out.println("结束");
                            handler.accept(new String(content));

                            //继续监听读取事件
                            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);
                            selectionKey.selector().wakeup();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();

                }
                System.out.println("remove...");
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
