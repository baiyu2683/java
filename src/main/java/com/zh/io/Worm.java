package com.zh.io;

import java.io.*;
import java.util.Random;

/**
 * 对象序列化和反序列化，反序列化不会调用对象的构造函数
 * 可以保存自身状态，包括所有引用的状态。。。
 * Created by zh on 2017-03-14.
 */
public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] datas = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private Worm next;
    private char c;
    //Value of i == number of segments
    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if(--i > 0)
            next = new Worm(i, (char)(x + 1));
    }
    public Worm() {
        System.out.println("Default constructor");
    }

    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for(Data data : datas) {
            result.append(data);
        }
        result.append(")");
        if(next != null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);
        //写入文件
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("f:/worm.out")
        );
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close();
        //读文件
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("f:/worm.out")
        );
        String s = (String)in.readObject();
        Worm w2 = (Worm)in.readObject();
        System.out.println(s + "w2 = " + w2);

        //写入内存
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(byteArrayOutputStream);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
        out2.flush();
        ObjectInputStream in2 = new ObjectInputStream(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray())
        );
        s = (String)in2.readObject();
        Worm w3 = (Worm)in2.readObject();
        System.out.println(s + "w3 = " + w3);
    }

}
class Data implements Serializable {
    private int n;
    public Data(int n) {
        this.n = n;
    }
    public String toString() {
        return Integer.toString(n);
    }
}
