package com.zh.chapter1.chapter13.exercise;

/**
 * 打印链表
 * @author zh
 * 2018年8月15日
 */
public class Utils {
	
	public static void show(Node node) {
		if (node != null) {
			System.out.println(node.item);
			show(node.next);
		}
	}

}
