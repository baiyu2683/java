package com.zh;

import org.junit.Test;

import com.zh.chapter1.chapter13.exercise.DeQueue;
import com.zh.chapter1.chapter13.exercise.Utils;

public class TestDeQueue {

    @Test
    public void testInsertHeade() {
        DeQueue<String> dq = new DeQueue<String>();
        dq.insertHead("zh1");
        System.out.println(dq.deleteHead());
    }
    
    @Test
    public void testInsertTail() {
        DeQueue<String> dq = new DeQueue<String>();
        dq.insertTail("zh1");
        dq.insertTail("zh2");
        System.out.println(dq.deleteTail());
        System.out.println(dq.deleteTail());
    }
    
    @Test
    public void testinsertAfter() {
    	DeQueue<String> dq = new DeQueue<String>();
    	dq.insertHead("zh1");
    	dq.insertHead("zh2");
    	dq.insertAfter("zh2", "zh3");
    	System.out.println(dq.deleteHead());
    	System.out.println(dq.deleteHead());
    	System.out.println(dq.deleteHead());
    }
    
    @Test
    public void testInsertBefore() {
    	DeQueue<String> dq = new DeQueue<String>();
    	dq.insertHead("zh1");
    	dq.insertHead("zh2");
    	dq.insertBefore("zh2", "zh3");
    	System.out.println(dq.deleteHead());
    	System.out.println(dq.deleteHead());
    	System.out.println(dq.deleteHead());
    }
    
    @Test
    public void testDelete() {
    	DeQueue<String> dq = new DeQueue<String>();
    	dq.insertHead("zh1");
    	dq.insertHead("zh2");
    	dq.insertBefore("zh2", "zh3");
    	dq.delete("zh3");
    	System.out.println(dq.size());
    	int size = dq.size();
    	for (int i = 0 ; i < size ; i++) {
    		System.out.println(dq.deleteHead());
    	}
    }
}
