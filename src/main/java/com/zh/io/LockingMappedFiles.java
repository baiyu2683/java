package com.zh.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by zh on 2017-03-12.
 */
public class LockingMappedFiles {
    static final int LENGTH = 0x8FFFFFF;
    static FileChannel fileChannel;
    public static void main(String[] args) throws IOException {
        fileChannel = new RandomAccessFile("f:/testra.dat", "rw").getChannel();
        MappedByteBuffer out =
                fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++)
            out.put((byte)'x');
        new LockAndModify(out, 0, 0 + LENGTH/3);
        new LockAndModify(out, LENGTH/2, LENGTH/2 + LENGTH/4);
    }
    private static class LockAndModify extends Thread {
        private ByteBuffer byteBuffer;
        private int start, end;
        LockAndModify(ByteBuffer byteBuffer, int start, int end) {
            this.start = start;
            this.end = end;
            byteBuffer.limit(end);
            byteBuffer.position(start);
            this.byteBuffer = byteBuffer.slice();
            start();//构造函数中启动线程
        }
        public void run() {
            try {
                //Exclusive lock with no overlap:
                FileLock fileLock = fileChannel.lock(start, end, false);
                //Perform modification
                while (byteBuffer.position() < byteBuffer.limit() - 1)
                    byteBuffer.put((byte)(byteBuffer.get() + 1));
                fileLock.release();
                System.out.println("Released: " + start + " to " + end);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
