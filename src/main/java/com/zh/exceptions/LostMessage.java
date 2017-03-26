package com.zh.exceptions;

/**
 * Created by zh on 2017-03-26.
 */
class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}
class HoHumException extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}
public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }
    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            LostMessage lostMessage = new LostMessage();
            try {
                lostMessage.f(); //异常丢失
            } finally {
//                lostMessage.dispose(); //dispose()的异常被捕获，导致f()抛出的异常丢失
                return; //导致f()抛出的异常丢失
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
