package com.zh.chapter1.chapter13;

import java.util.Iterator;

/**
 * 下压栈(链表实现)
 * @author zh
 * 2018年8月12日
 */
public class Stack<T> implements Iterable<T> {
	
	/**
	 * 链表节点
	 * @author zh
	 * 2018年8月12日
	 */
	private class Node {
		T item;
		Node next;
	}
	
	private Node first; // 栈顶
	
	private int N; // 元素个数
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void push(T t) {
		Node new_first = new Node();
		new_first.item = t;
		new_first.next = first;
		first = new_first;
		N++;
	}
	
	public T pop() {
		T temp = first.item;
		first = first.next;
		N--;
		return temp;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T> {
		
		private Node current_node = first;

		@Override
		public boolean hasNext() {
			return current_node != null;
		}

		@Override
		public T next() {
			T next = current_node.item;
			current_node = current_node.next;
			return next;
		}
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		for (String s : stack) {
			System.out.println(s);
		}
	}
}
