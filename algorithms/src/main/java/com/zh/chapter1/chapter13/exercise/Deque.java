package com.zh.chapter1.chapter13.exercise;

/**
 * 队列
 * @author zh
 * 2018年8月16日
 */
public interface Deque<T> {

	boolean isEmpty();
	
	int size();
	
	void pushLeft(T t);
	
	void pushRight(T t);
	
	T popLeft();
	
	T popRight();
}
