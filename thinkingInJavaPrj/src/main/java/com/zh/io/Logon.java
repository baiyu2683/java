package com.zh.io;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-03-14.
 */
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;
    public Logon(String name, String pwd) {
        this.username = name;
        this.password = pwd;
    }
    public String toString() {
        return "logon info : \n username: " + username +
                "\n date: " + date + "\n password: " + password;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Logon logon = new Logon("Hulk", "myLittlePony");
        System.out.println("logon = " + logon);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("f:/Logon.out")
        );
        o.writeObject(logon);
        o.close();
        TimeUnit.SECONDS.sleep(1);

        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("f:/Logon.out")
        );
        System.out.println("Recoving object at " + new Date());
        logon = (Logon)in.readObject();
        System.out.println("logon = " + logon);
    }
}
