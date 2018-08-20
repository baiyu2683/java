package com.zh.chapter1.chapter13.exercise;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 一个可迭代的栈，包含一个静态copy方法，返回此栈的一个副本
 * @author zh
 * 2018年8月13日
 */
public class IterableStack<T> implements Iterable<T> {
	
	private class Node {
		T item;
		Node next;
	}
	
	private Node first;
	private int N;
	private int modCount; //修改次数，快速出错
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(T t) {
		Node new_node = new Node();
		new_node.item = t;
		if (first == null) {
			first = new_node;
		} else {
			new_node.next = first;
			first = new_node;
		}
		N++;
		modCount++;
	}
	
	public T pop() {
		if (first == null) return null;
		T t = first.item;
		first = first.next;
		N--;
		modCount++;
		return t;
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr(modCount);
	}
	
	private class Itr implements Iterator<T> {
		
		private Node current_node = first;
		private int modCount;
		
		public Itr(int modCount) {
			this.modCount = modCount;
		}

		@Override
		public boolean hasNext() {
			if (this.modCount != IterableStack.this.modCount) {
				throw new ConcurrentModificationException();
			}
			return current_node != null;
		}

		@Override
		public T next() {
			// 内部类访问外部类变量格式: 外部类名.this.xxx
			if (this.modCount != IterableStack.this.modCount) {
				throw new ConcurrentModificationException();
			}
			T item = current_node.item;
			current_node = current_node.next;
			return item;
		}
	}
	
	public static <T> IterableStack<T> copy(IterableStack<T> src) {
		IterableStack<T> copy = new IterableStack<>();
		for (T t : src) {
			copy.push(t);
		}
		return copy;
	}

	public static void main(String[] args) {
		IterableStack<String> is = new IterableStack<>();
		is.push("1");
		is.push("2");
		is.push("3");
		IterableStack<String> copy = IterableStack.copy(is);
		for (String s : copy) {
			System.out.println(s);
		}
		// 快速出错
		for (String s : copy) {
			System.out.println(s);
			copy.push("1");
		}
	}
}
