package com.zh.chapter1.chapter13.exercise;

import java.util.Stack;

/**
 * 栈实现的队列
 * @author zh
 * 2018年8月20日
 */
public class StackQueue<T> {

	private Stack<T> datas = new Stack<>();
	private Stack<T> temps = new Stack<>();
	
	public void enqueue(T t) {
		datas.push(t);
	}
	
	public T dequeue() {
		int size = datas.size();
		for (int i = 0 ; i < size - 1 ; i++) {
			temps.push(datas.pop());
		}
		T t = datas.pop();
		for (int i = 0 ; i < size - 1 ; i++) {
			datas.push(temps.pop());
		}
		return t;
	}
	
	public int size() {
		return datas.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public static void main(String[] args) {
		StackQueue<String> queue = new StackQueue<>();
		for (int i = 0 ; i < 100 ; i++) {
			queue.enqueue(String.valueOf(i));
		}
		StringBuilder sb = new StringBuilder();
		int size = queue.size();
		for (int i = 0 ; i < size ; i++) {
			sb.append(queue.dequeue() + ",");
		}
		System.out.println(sb.toString());
	}
}
