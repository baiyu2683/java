package com.zh.chapter1.chapter13.exercise;

/**
 * 翻转一个链表
 * @author zh
 * 2018年8月15日
 */
public class Exercise1330 {
	
	public static void main(String[] args) {
		Node first = new Node();
		first.item = "0";
		
		Node currentNode = first;
		for (int i = 1 ; i < 11 ; i++) {
			Node node = new Node();
			node.item = i + "";
			currentNode.next = node;
			
			currentNode = node;
		}
		Utils.show(first);
		System.out.println("=========");
		Utils.show(reverse(first));
		Utils.show(reverse2(first));
	} 
	
	/**
	 * 翻转链表(破坏性的)
	 * @param first
	 * @return
	 */
	private static Node reverse2(Node first) {
		Node oldFirst = first;
		Node newFirst = null;
		while (oldFirst != null) {
			Node second = oldFirst.next;
			oldFirst.next = newFirst;
			newFirst = oldFirst;
			oldFirst = second;
		}
		return newFirst;
	}

	/**
	 * 这个写法需要申请空间
	 * 不会破坏原来的链表
	 * @param first
	 * @return
	 */
	public static Node reverse(Node first) {
		if (first == null) return null;
		Node newFirst = new Node();
		newFirst.item = first.item;
		
		Node currentNode = newFirst;
		while(first.next != null) {
			Node node = first.next;
			first = first.next;
			
			Node newNode = new Node();
			newNode.item = node.item;
			
			newNode.next = currentNode;
			currentNode = newNode;
		}
		return currentNode;
	}
}
