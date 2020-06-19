package com.zh;

import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ByteProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Random;

public class ByteBufTest {

    /**
     * 默认256，最大Integer.MAX_VALUE
     */
    @Test
    public void test1() {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeByte(1);
        byteBuf.writeByte(2);
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.capacity());
        byteBuf.writeByte(3);
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.capacity());
    }

    /**
     *
     */
    @Test
    public void compositeBufferTest() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();

        ByteBuf headerBuf = Unpooled.buffer();
        headerBuf.writeBytes("123".getBytes());
        ByteBuf bodyBuf = Unpooled.buffer();
        bodyBuf.writeBytes("asd".getBytes());

        // 添加时需要设置increaseWriterIndex参数为true，否则始终为0无法读取
        messageBuf.addComponents(true,headerBuf, bodyBuf);
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
        byte[] arr = new byte[messageBuf.readableBytes()];
        messageBuf = messageBuf.getBytes(0, arr);
        System.out.println(new String(arr));
    }

    @Test
    public void byteOperation() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes("asdfasdfasdf".getBytes());
        for (int i = 0 ; i < buffer.capacity() ; i++) {
            // 此方法不会改变读索引和写索引
            byte b = buffer.getByte(i);
            System.out.println((char)b);
        }
    }

    /**
     * 测试可写字节
     */
    @Test
    public void testLineRead() {
        Random random = new Random();
        ByteBuf byteBuf = Unpooled.buffer();
        while (byteBuf.writableBytes() >= 4) {
            byteBuf.writeInt(random.nextInt());
        }
    }

    /**
     * 索引管理
     * reset*()和clear()会重置索引但不会清空内容
     */
    @Test
    public void indexOper() {
        ByteBuf byteBuf = Unpooled.buffer(8);
        byteBuf.writeInt(1);
        // 4字节
        Assert.assertEquals(byteBuf.writerIndex(), 4);
        byteBuf.resetWriterIndex();
        Assert.assertEquals(byteBuf.writerIndex(), 0);
        byteBuf.writeInt(1);
        byteBuf.writeInt(2);
        Assert.assertEquals(byteBuf.writerIndex(), 8);
        byteBuf.readInt();
        Assert.assertEquals(byteBuf.readerIndex(), 4);
        byteBuf.clear();
        Assert.assertEquals(byteBuf.readerIndex(), 0);
        Assert.assertEquals(byteBuf.writerIndex(), 0);
    }

    @Test
    public void searchOperation() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes("asdf\r\nasdf".getBytes());
        Assert.assertEquals(buffer.forEachByte(ByteProcessor.FIND_CRLF), 4);
        System.out.println(buffer.indexOf(0, buffer.capacity(), (byte)'d'));
    }

    /**
     * 派生缓冲区
     */
    @Test
    public void deriveBuffer() {
        ByteBuf byteBuf = Unpooled.buffer(32);
        byteBuf.writeBytes("fdsetyua".getBytes());
        byteBuf.readChar();
        Assert.assertEquals(byteBuf.writerIndex(), 8);
        Assert.assertEquals(byteBuf.readerIndex(), 2);
        // duplicate()会复制读索引和写索引
        ByteBuf tmp = byteBuf.duplicate();
        Assert.assertEquals(tmp.writerIndex(), 8);
        Assert.assertEquals(tmp.readerIndex(), 2);
        // 会从读索引开始进行slice
        tmp = byteBuf.slice();
        Assert.assertEquals(tmp.writerIndex(), 6);
        Assert.assertEquals(tmp.readerIndex(), 0);
        // 读索引从0开始，写索引在最后
        tmp = byteBuf.slice(0, byteBuf.writerIndex());
        Assert.assertEquals(tmp.writerIndex(), 8);
        Assert.assertEquals(tmp.readerIndex(), 0);
        // 不改变读索引和写索引
        tmp = byteBuf.order(ByteOrder.BIG_ENDIAN);
        System.out.println(tmp.readChar());
        System.out.println(tmp.readerIndex());
        // 从当前读索引开始，根据给定的长度切一个缓冲区
        tmp = byteBuf.readSlice(4);
        System.out.println(tmp.readChar());
    }

    /**
     * 制造一种writerindex在readerindex之前的情况
     */
    @Test
    public void resetMethod() {
        try {
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeInt(1); // - rdx: 0 wdx: 4
            byteBuf.readInt(); // -rdx: 4 wdx: 4
            byteBuf.resetWriterIndex(); // - rdx: 4 wdx: 0
            byteBuf.readInt();
            Assert.fail();
        } catch (Exception e) {
            // ignore exception
        }
    }

    /**
     * 比较copy()和派生的方法不同
     * copy()出的缓冲区会有自己的数据副本，派生的是共享数据区的
     */
    @Test
    public void copyTest() {
        Charset charset = Charset.forName("utf-8");
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!", charset);
        ByteBuf sliced = byteBuf.slice(0, 15);
        System.out.println(sliced.toString(charset));
        byteBuf.setByte(0, (byte)'J');
        // 切片后，两个缓冲区实际上是共享同一块区域的
        assert byteBuf.getByte(0) == sliced.getByte(0);

        // copy后返回的ByteBuf拥有独立的数据副本
        ByteBuf copy = byteBuf.copy();
        byteBuf.setByte(0, (byte)'z');
        assert byteBuf.getByte(0) != sliced.getByte(0);
    }

    /**
     * 测试ByteBufUtil工具类
     */
    @Test
    public void byteBufUtil() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeShort(0x0101);
        System.out.println(ByteBufUtil.hexDump(byteBuf));
    }

    /**
     * 引用计数
     * TODO 这个原理需要深入看
     */
    @Test
    public void referCount() {
        Channel channel = new NioSocketChannel();
        ByteBuf buffer = channel.alloc().buffer();
        buffer.writeShort(1);
        assert buffer.refCnt() == 1;
        buffer.release();
        assert  buffer.refCnt() == 0;
    }
}
