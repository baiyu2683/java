package com.zh;

import org.junit.Test;

import java.net.*;

public class TestCases {

    @Test
    public void getMacAddress() throws UnknownHostException, SocketException {
        InetAddress ia = InetAddress.getLocalHost();
        byte[] maxAddress = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        System.out.println(new String(maxAddress));
    }
}
