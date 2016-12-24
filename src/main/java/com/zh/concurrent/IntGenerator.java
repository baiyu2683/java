package com.zh.concurrent;

/**
 * Created by zh on 2016/12/25.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();

    public void cancel() {
        canceled = true;
    }
    public boolean isCanceled(){
        return canceled;
    }
}
