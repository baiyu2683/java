package com.zh;

import org.junit.Test;

import java.math.BigDecimal;
import java.net.*;

public class TestCases {

    @Test
    public void getMacAddress() throws UnknownHostException, SocketException {
        System.out.println(2 / 3f);
        System.out.println(Math.round(2 / 3f));
    }

    @Test
    public void bigDecimal() {
        BigDecimal bd = new BigDecimal(127.1123123d);
        System.out.println(bd.floatValue());
    }

    @Test
    public void insertWhiteSpace() {
        System.out.println("宋体".replace("", " ").trim());
    }
}
