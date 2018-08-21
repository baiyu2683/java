package com.zh;

import org.junit.Test;

import com.zh.chapter1.chapter13.exercise.ResizingArrayQueue;

public class TestResizingArrayQueue {

	@Test
	public void testpush() {
		ResizingArrayQueue<String> raq = new ResizingArrayQueue<>();
		raq.pushLeft("1");
		raq.pushRight("4");
		raq.pushLeft("3");
		raq.pushLeft("2");
		raq.pushLeft("0");
		raq.pushLeft("-1");
		raq.pushLeft("-2");
		raq.pushLeft("5");
		raq.pushRight("7");
		raq.pushRight("9");
		raq.pushLeft("-3");
		System.out.println(raq.toString());
		System.out.println(raq.popLeft());
		System.out.println(raq.popLeft());
		System.out.println(raq.popLeft());
		System.out.println(raq.popRight());
		System.out.println(raq.popRight());
		System.out.println(raq.toString());
	}
}
