package com.zh;

import org.junit.Test;

import com.zh.chapter1.chapter13.exercise.DeQueue;

public class TestDeQueue {

    @Test
    public void testInsertHeade() {
        DeQueue<String> dq = new DeQueue<String>();
        dq.insertHead("zh1");
        System.out.println(dq.deleteHead());
    }
    
    @Test
    public void testInsertTail() {
        
    }
}
