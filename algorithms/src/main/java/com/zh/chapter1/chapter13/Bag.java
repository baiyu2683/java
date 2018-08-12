package com.zh.chapter1.chapter13;

import java.util.Iterator;

/**
 * 背包，利用链表实现
 * @author zh
 * 2018年8月12日
 */
public class Bag<T> implements Iterable<T> {

	private class Node {
		T item;
		Node next;
	}
	
	private Node first;
	
	public void add (T t) {
		Node new_first = new Node();
		new_first.item = t;
		new_first.next = first;
		first = new_first;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T> {
		
		private Node current_node = first;

		@Override
		public boolean hasNext() {
			return current_node != null;
		}

		@Override
		public T next() {
			T t = current_node.item;
			current_node = current_node.next;
			return t;
		}
	}
	
	public static void main(String[] args) {
		Bag<String> bag = new Bag();
		bag.add("1");
		bag.add("2");
		bag.add("3");
		for (String s : bag) {
			System.out.println(s);
		}
	}
}
