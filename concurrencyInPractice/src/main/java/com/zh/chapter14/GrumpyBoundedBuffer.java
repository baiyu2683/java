package com.zh.chapter14;

/**
 * 不满足条件时抛出异常，这需要调用者进行异常处理，使用比较复杂
 * Created by zh on 2017-10-29.
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferFullException {
        if(isFull())
            throw new BufferFullException();
        doPut(v);
    }
    public synchronized V take() throws BufferFullException {
        if(isEmpty())
            throw new BufferEmptyException();
        return doTake();
    }
}
