package com.zh.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by zh on 2017-07-02.
 */
public class HelloServer {
    public static void main(String[] args) {
        try {
            IHello iHello = new HelloImpl();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/IHello", iHello);
            System.out.println("远程IHello对象绑定成功!");
        } catch (RemoteException e) {
            System.out.println("创建远程对象时发生异常!");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("url异常");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("重复绑定异常");
            e.printStackTrace();
        }
    }
}
