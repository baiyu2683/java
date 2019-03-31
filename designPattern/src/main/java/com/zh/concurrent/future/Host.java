package com.zh.concurrent.future;

public class Host {

    public Data request(final int count, final char c, FutureDataListener listener) {
        System.out.println("request(" + count + ", " + c + ") BEGIN");

        final FutureData futureData = new FutureData();
        futureData.addListener(listener);
        // 这里实现操作的异步
        new Thread(() -> {
            RealData realData = new RealData(count, c);
            futureData.setReadData(realData);
        }).start();

        System.out.println("request(" + count + ", " + c + ") END");
        return futureData;
    }
}
