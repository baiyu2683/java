package com.zh.chapter1.chapter13;

/**
 * 链表实现队列
 * @author zh
 * 2018年8月12日
 */
public class Queue<T> {

	private class Node {
		T item;
		Node next;
	}
	
	private Node first;
	private Node last;
	private int N;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(T t) {
		Node new_last = new Node();
		new_last.item = t;
		if (isEmpty()) {
			first = last = new_last;
		} else {
			last.next = new_last;
			last = new_last;
		}
		N++;
	}
	
	public T pop() {
		T t = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		N--;
		return t;
	}
	
	public static void main(String[] args) {
		Queue<String> queue = new Queue<>();
		queue.enqueue("1");
		queue.enqueue("2");
		queue.enqueue("3");
		System.out.println(queue.pop() + ", " + queue.pop() + ", " + queue.pop());
	}
}
