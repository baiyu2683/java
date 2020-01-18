package com.zh.reference;

import com.zh.guava.TestGuavaCache;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author zhangheng
 * @date 2019/12/07
 */
public class PhantomRefrenceTest {

    public static void main(String[] args) {
        TestGuavaCache testGuavaCache = new TestGuavaCache();
        ReferenceQueue<TestGuavaCache> testGuavaCacheReferenceQueue = new ReferenceQueue<>();
        PhantomReference<TestGuavaCache> reference = new PhantomReference<>(testGuavaCache, testGuavaCacheReferenceQueue);
        System.out.println(reference.get());
        System.out.println(testGuavaCacheReferenceQueue.poll());
    }
}
