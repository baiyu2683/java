package com.zh.chapter1.chapter13.exercise;

/**
 * @author zh
 * 2018年8月19日
 */
public interface GeneralizedQueue<T> {

	boolean isEmpty();

	void insert(T t);
	
	T delete(int k);
}
