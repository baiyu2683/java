package com.zh.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zh on 2017-07-02.
 */
public interface IHello extends Remote {

    public String helloWorld() throws RemoteException;

    public String sayHelloToSomeBody(String someBodyName) throws RemoteException;
}
