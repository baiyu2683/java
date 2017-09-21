package com.zh.chapter7;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 实现Callable，并添加取消和构造runnablefuture的方法
 * Created by zh on 2017-09-21.
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
