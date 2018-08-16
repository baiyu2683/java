package com.zh.chapter1.chapter13.exercise;

/**
 * 接收一个参数k， 从Queue中找到导出第k个元素
 * @author zh
 * 2018年8月14日
 */
public class Exercise1315 {

	public static void main(String[] args) {
		ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
		for (int i = 0 ; i < 100 ; i++) {
			queue.enqueue("zh" + i);
		}
		
		// 倒数第k个元素, 即正数第 size - k + 1个元素
		int size = queue.size() - 10;
		for (int i = 0 ; i < size ; i++) {
			queue.dequeue();
		}
		System.out.println(queue.dequeue());
	}
}
