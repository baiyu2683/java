package com.zh.chapter1.chapter13.exercise;

/**
 * 删除链表的尾节点
 * @author zh
 * 2018年8月14日
 */
public class Exercies1219 {
	private static class Node {
		String item;
		Node next;
	}
	
	public static void main(String[] args) {
		Node first = new Node();
		first.item = "0";
		Node current_node = first;
		Node temp_node = null;
		for (int i = 1 ; i < 20 ; i++) {
			Node node = new Node();  
			node.item = String.valueOf(i);
			current_node.next = node;
			current_node = node;
			// 缓存一个节点，用于测试方法
			if (i == 11) temp_node = node;
		}
//		show(first);
		System.out.println("=======================");
//		show(deleteOn(1, first));
//		System.out.println(find("10", first));
//		removeAfter(temp_node);
		Node newNode = new Node();
		newNode.item = "插入的节点";
		insertAfter(temp_node, newNode);
		show(first);
	}
	
	/**
	 * 将一个节点插入到链表中某个节点之后
	 * @param before
	 * @param after
	 */
	private static void insertAfter(Node before, Node after) {
		if (before == null || after == null) return;
		
		after.next = before.next;
		before.next = after;
	}
	
	/**
	 * 删除链表中节点node之后的节点
	 * @param node
	 * @param first
	 */
	private static void removeAfter(Node node) {
		if (node == null) return;
		node.next = null;
	}
	
	/**
	 * 删除第k个节点，从1开始
	 */
	private static Node deleteOn(int k, Node first) {
		Node temp_before = first;
		Node temp_after = temp_before.next;
		if (k == 1) {
			if (temp_before != null) {
				return temp_after;
			}
		}
		for (int i = 2 ; i <= k ; i++) {
			if (i == k) {
				if (temp_after != null) {
					temp_before.next = temp_after.next;
				}
				return first;
			} else {
				if (temp_after != null) {
					temp_before = temp_after;
					temp_after = temp_after.next;
				} else {
					return first;
				}
			}
		}
		return first;
	}
	
	/**
	 * 删除链表最后一个元素
	 * @param first
	 */
	private static void deleteTail(Node first) {
		// 删除末尾节点
		Node temp_before = first;
		if (first == null) return; // 链表长度为0
		Node temp_after = temp_before.next;
		while (temp_after.next != null) {
			temp_before = temp_after;
			temp_after = temp_after.next;
		}
		temp_before.next = null;
	}
	
	/**
	 * 查找是否存在与k相等的元素
	 * @param k
	 * @param first
	 * @return
	 */
	private static boolean find(String k, Node first) {
		Node temp = first;
		while(temp != null) {
			if (k.equals(temp.item)) {
				 return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	private static void show(Node node) {
		if (node != null) {
			System.out.println(node.item);
			show(node.next);
		}
	}
}
