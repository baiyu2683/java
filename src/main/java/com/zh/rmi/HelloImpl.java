package com.zh.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by zh on 2017-07-02.
 */
public class HelloImpl extends UnicastRemoteObject implements IHello {

    protected HelloImpl() throws RemoteException {
    }

    @Override
    public String helloWorld() throws RemoteException {
        return "Hello World";
    }

    @Override
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException {
        return "Hello, " + someBodyName + "!";
    }
}
