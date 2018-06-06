package com.zh.io;

import com.zh.util.CountingGenerator;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * IO性能测试
 * Created by zh on 2017-03-12.
 */
public class MappedIO {
    private static int numOfInts = 4000000;
    private static int numOfUbuffInts = 2000000;
    private abstract static class Tester {
        private String name;
        public Tester(String name) {this.name = name;}
        public void runTest() {
            System.out.println(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration/1.0e9);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = {
            new Tester("Stream Write") {
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream("f:/temp.tmp")
                            )
                    );
                    for (int i = 0; i < numOfInts; i++)
                        dos.writeInt(i);
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile("f:/temp.tmp", "rw").getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size())
                            .asIntBuffer();
                    for (int i = 0; i < numOfInts; i++)
                        intBuffer.put(i);
                    fileChannel.close();
                }
            },
            new Tester("Stream Read") {
                public void test() throws IOException {
                    DataInputStream dataInputStream = new DataInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("f:/temp.tmp")
                            )
                    );
                    for (int i = 0; i < numOfInts; i++)
                        dataInputStream.readInt();
                    dataInputStream.close();
                }
            },
            new Tester("Mapped Read") {
                public void test() throws IOException {
                    FileChannel fileChannel = new FileInputStream(
                            new File("f:/temp.tmp")
                    ).getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size())
                            .asIntBuffer();
                    while (intBuffer.hasRemaining())
                        intBuffer.get();
                    fileChannel.close();
                }
            },
            new Tester("Stream Read/Write") {
                public void test() throws IOException {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(
                            new File("f:/temp.tmp"), "rw"
                    );
                    randomAccessFile.writeInt(1);
                    for (int i = 0; i < numOfUbuffInts; i++) {
                        randomAccessFile.seek(randomAccessFile.length() - 4);
                        randomAccessFile.writeInt(randomAccessFile.readInt());
                    }
                }
            },
            new Tester("Mapped Read/Write") {
                public void test() throws IOException {
                    FileChannel fileChannel = new RandomAccessFile(
                            new File("f:/temp.tmp"), "rw"
                    ).getChannel();
                    IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size())
                            .asIntBuffer();
                    intBuffer.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++)
                        intBuffer.put(intBuffer.get(i - 1));
                    fileChannel.close();
                }
            }
    };

    public static void main(String[] args) {
        for (Tester test : tests)
            test.runTest();
    }
}
