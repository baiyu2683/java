package com.zh.chapter1.chapter13.exercise;

/**
 * 链表实现的队列
 * @author zh
 * 2018年8月19日
 */
public class LinkedGeneralizedQueue<T> implements GeneralizedQueue<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int N;

	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	@Override
	public void insert(T t) {
		if (head == null) {
			head = new Node<T>();
			head.item = t;
			tail = head;
		} else {
			Node<T> temp = new Node<>();
			temp.item = t;
			tail.next = temp;
			tail = temp;
		}
		N++;
	}

	@Override
	public T delete(int k) {
		Node<T> node = head;
		int i = 1;
		while(node != null) {
			if (k == i) return node.item;
			else node = node.next;
			i++;
		}
		return null;
	}

	public static void main(String[] args) {
		GeneralizedQueue<Integer> queue = new LinkedGeneralizedQueue<>();
		for (int i = 0 ; i < 100 ; i++) {
			queue.insert(i);
		}
		System.out.println(queue.delete(10));
	}
}
