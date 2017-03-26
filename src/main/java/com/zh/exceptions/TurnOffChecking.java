package com.zh.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zh on 2017-03-26.
 */
class WrapCheckedException {
    void throwRuntimeException(int type) {
        try {
            switch (type) {
                case 0: throw new FileNotFoundException();
                case 1: throw new IOException();
                case 2: throw new RuntimeException("where am I?");
                default: return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e); //貌似是个类似适配器的东西。。。
        }
    }
}
class SomeOtherException extends Exception {}

public class TurnOffChecking {
    public static void main(String[] args) {
        WrapCheckedException wce = new WrapCheckedException();
        wce.throwRuntimeException(3);
        for (int i = 0; i < 4; i++) {
            try {
                if(i < 3)
                    wce.throwRuntimeException(i);
                else
                    throw new SomeOtherException();
            } catch (SomeOtherException e) {
                System.out.println("SomeOtherException: " + e);
            } catch (RuntimeException e) {  //运行期异常可以不捕获
                try {
                    throw e.getCause();   //可以使用getCause()获得异常包装的原异常，然后捕获
                } catch (FileNotFoundException e1) {
                    System.out.println("FileNotFoundException: " + e1);
                } catch (IOException e2) {
                    System.out.println("IOException: " + e2);
                } catch (Throwable e3) {
                    System.out.println("Throwable: " + e3);
                }
            }
        }
    }
}
