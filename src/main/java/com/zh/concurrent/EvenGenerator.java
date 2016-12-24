package com.zh.concurrent;

/**
 * Created by zh on 2016/12/25.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public int next() {
        /**
         * 1. 递增操作不是原子操作
         * 2. 两个递增更不是院子操作，可能出现一个线程递增一次后另外一个线程就使用的情况，此时为奇数
         */
        ++currentEvenValue;  //Danger point here!
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
