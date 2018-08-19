package com.zh.chapter1.chapter13.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 前移编码策略，
 * @author zh
 * 2018年8月19日
 */
public class MoveToFront<T> implements Iterable<T> {

	private Node<T> head;
	
	public void insert(T t) {
		if (head == null) {
			Node<T> temp = new Node<>();
			temp.item = t;
			head = temp;
		} else {
			Node<T> before = head;
			Node<T> after = before.next;
			if (!t.equals(before.item)) { // 如果第一个节点就是重复节点，什么都不做
				while(after != null) {
					if (t.equals(after.item)) {
						before.next = after.next;
						after.next = head;
						head = after;
						return;
					}
					before = after;
					after = after.next;
				}
			}
			Node<T> temp = new Node<>();
			temp.item = t;
			temp.next = head;
			head = temp;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new MoveToFrontIterator(head);
	}
	
	private class MoveToFrontIterator implements Iterator<T> {
		
		private Node<T> node;
		
		protected MoveToFrontIterator(Node<T> node) {
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			T t = node.item;
			node = node.next;
			return t;
		}
	}
	
	public static void main(String[] args) {
		Random random = new Random(47);
		MoveToFront<Integer> mtf = new MoveToFront<>();
		for (int i = 0 ; i < 20 ; i++) {
			mtf.insert(random.nextInt(200));
			StringBuilder sb = new StringBuilder();
			for (Integer j : mtf) {
				sb.append(j + ",");
			}
			System.out.println(sb.toString());
		}
		
	}
}
