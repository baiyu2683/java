package com.zh.concurrent.future;

/**
 * 实际获取内容操作，这里是阻塞耗时操作
 */
public class RealData implements Data {

    private final String content;

    public RealData(int count, char c) {
        System.out.println("making RealData(" + count + ", " + c + ") BEGIN");
        char[] buffer = new char[count];
        for (int i = 0 ; i < count ; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
            }
        }
        System.out.println("making ReadData(" + count + ", " + c + ") END");
        this.content = new String(buffer);
    }

    @Override
    public String getContent() {
        return content;
    }
}
