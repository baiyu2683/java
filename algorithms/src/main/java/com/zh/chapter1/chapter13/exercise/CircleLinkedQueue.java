package com.zh.chapter1.chapter13.exercise;

/**
 * 环形链表实现的队列
 * @author zh
 * 2018年8月15日
 */
public class CircleLinkedQueue {
	
	private class Node {
		private String item;
		private Node next;
	}
	
	private Node last;
	
	public void enqueue(String item) {
		Node newNode = new Node();
		newNode.item = item;
		if (last == null) {
			last = newNode;
			last.next = newNode;  // 只有一个节点
		} else {
			// 插入队尾
			newNode.next = last.next;
			last.next = newNode;
			last = newNode;
		}
	}
	
	public String dequeue() {
		if (last == null) {
			return null;
		} else {
			String item = last.next.item;
			if (last.next == last) {
				last = null;
			} else {
				last.next = last.next.next;
			}
			return item;
		}
	}
	
	public boolean isEmpty() {
		return last == null;
	}
	
	public int size() {
		if (isEmpty()) return 0;
		else {
			if (last == last.next) return 1;
			else {
				int num = 0;
				Node temp = last;
				do {
					temp = temp.next;
					num++;
				} while(temp != last);
				return num;
			}
		}
	}
	
	public static void main(String[] args) {
		CircleLinkedQueue clq = new CircleLinkedQueue();
		System.out.println(clq.size());
		System.out.println(clq.isEmpty());
		for(int i = 0 ; i < 100 ; i++) {
			clq.enqueue(i + "");
		}
		System.out.println(clq.size());
		System.out.println(clq.isEmpty());
		System.out.println(clq.dequeue());
		System.out.println(clq.dequeue());
		System.out.println(clq.size());
		System.out.println(clq.isEmpty());
	}
}
