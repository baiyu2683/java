package com.zh.chapter1.chapter13.exercise;

/**
 * 删除链表的尾节点
 * 
 * 有很多测试，这里的Node里的item只能是数字
 * @author zh
 * 2018年8月14日
 */
public class Exercies1319 {
	
	public static void main(String[] args) {
		Node first = new Node();
		first.item = "0";
		Node current_node = first;
		Node temp_node = null;
		for (int i = 1 ; i < 200 ; i++) {
			Node node = new Node();  
			node.item = String.valueOf(i);
			current_node.next = node;
			current_node = node;
			// 缓存一个节点，用于测试方法
			if (i == 19) temp_node = node;
		}
//		Utils.show(first);
		System.out.println("=======================");
//		Utils.show(deleteOn(1, first));
//		System.out.println(find("10", first));
//		removeAfter(temp_node);
//		// 插入一个节点
//		Node newNode = new Node();
//		newNode.item = "zh";
//		insertAfter(temp_node, newNode);
//		Node newNode2 = new Node();
//		newNode2.item = "zh";
//		insertAfter(temp_node, newNode2);
//		Utils.show(first);
//		
//		// 删除item为zh的节点
//		Utils.show(remove("19", first));
		
		// 获得最大值
//		System.out.println(max(first));
		
		// 递归获取最大值
		System.out.println(recursionMax(first));
	}
	
	/**
	 * 递归求取队列中的最大值
	 * @param first
	 * @return
	 */
	private static int recursionMax(Node first) {
		int max = Integer.MIN_VALUE;
		if(first == null) return -1;
		else {
			max = Integer.valueOf(first.item);
			int sub_max = recursionMax(first.next);
			if (sub_max > max) max = sub_max;
		}
		return max;
	}
	
	/**
	 * 顺序求取链表中的最大值
	 * @param first
	 * @return
	 */
	private static int max(Node first) {
		if (first == null) return -1;
		
		int max = Integer.MIN_VALUE;
		Node current = first;
		int current_value = 0;
		while (current != null) {
			current_value = Integer.valueOf(current.item);
			if (current_value > max) max = current_value;
			current = current.next;
		}
		return max;
	}
	
	/**
	 * 删除item为k的节点
	 * @param k
	 * @param first
	 */
	private static Node remove(String k, Node first) {
		if (first == null) return null;
		else {
			if (k.equals(first.item)) {
				first = first.next;
			}
			if (first == null) return first;
			else {
				Node before = first;
				Node after = first.next;
				while (after != null) {
					if (k.equals(after.item)) {
						// 删除
						before.next = after.next;
						after = after.next;
					} else {
						before = after;
						after = after.next;
					}
				}
			}
		}
		return first;
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
}
