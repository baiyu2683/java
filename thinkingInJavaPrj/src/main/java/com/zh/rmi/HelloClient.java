package com.zh.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by zh on 2017-07-02.
 */
public class HelloClient {
    public static void main(String[] args) {
        try {
            IHello iHello = (IHello) Naming.lookup("rmi://localhost:8888/IHello");
            System.out.println(iHello.helloWorld());
            System.out.println(iHello.sayHelloToSomeBody("张恒"));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
