package com.zh.chapter1.chapter13.exercise;

/**
 * 打印链表
 * @author zh
 * 2018年8月15日
 */
public class Utils {
	
	public static void show(Node node, StringBuffer sb) {
		if (node != null) {
			sb.append(node.item + ", ");
			show(node.next, sb);
		}
	}
	
	public static void show(Node node) {
		StringBuffer sb = new StringBuffer();
		show(node, sb);
		System.out.println(sb.toString());
	}

}
