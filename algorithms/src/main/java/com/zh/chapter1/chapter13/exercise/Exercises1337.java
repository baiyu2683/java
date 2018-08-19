package com.zh.chapter1.chapter13.exercise;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * 接受两个参数N和M， N表示队列数量，M表示被剔除的位置，直到最后一个
 * @author zh
 * 2018年8月19日
 */
public class Exercises1337 {

	public static void main(String[] args) {
		int N = 10;
		int M = 3;
		Random random = new Random(System.nanoTime());
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0 ; i < 10 ; i++) {
			queue.add(i);
		}
		StringBuilder sb = new StringBuilder();
		while(queue.size() > 1) {
			Queue<Integer> temp = new ArrayDeque<>(queue);
			for (Integer i : temp) {
				int m = random.nextInt(10);
				System.out.println(i + "-" + m);
				if (m == M) {
					sb.append(i + ", ");
					queue.remove(i);
				}
			}
		}
		System.out.println(sb.toString());
		System.out.println(queue.poll());
		System.out.println(queue.size());
	}
}
