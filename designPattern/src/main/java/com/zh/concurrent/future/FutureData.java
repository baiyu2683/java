package com.zh.concurrent.future;

/**
 * 实现Future的操作，通过等待和唤醒标记实现
 */
public class FutureData implements Data {

    private RealData realData = null;
    private boolean ready = false;
    private FutureDataListener futureDataListener;

    public void addListener(FutureDataListener listener) {
        this.futureDataListener = listener;
    }

    public synchronized void setReadData(RealData readData) {
        if (ready) {
            return;
        }
        this.realData = readData;
        this.ready = true;
        notifyAll();
        futureDataListener.call(this);
    }

    @Override
    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.getContent();
    }
}
