package com.zh.chapter1.chapter13.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 可扩容随机队列
 * 删除时，随机交换某个元素和末尾元素，然后删除末尾元素并返回
 * @author zh
 * 2018年8月19日
 */
public class RandomQueue<T> implements Iterable<T> {

	private T[] datas;
	private int headIndex;
	private int N;
	
	private Random random;
	
	public RandomQueue() {
		datas = (T[]) new Object[8];
		random = new Random(System.nanoTime());
	}
	
	boolean isEmpty() {
		return N == 0;
	}
	
	public void enqueue(T t) {
		if (headIndex + N >= datas.length) {
			// 扩容
			resize(N * 2);
		}
		datas[headIndex + N] = t;
		N++;
	}

	public T dequeue() {
		int randomIndex = randomIndex();
		T random = datas[randomIndex];
		datas[randomIndex] = datas[N - 1];
		datas[N - 1] = null;
		N--;
		return random;
	}
	
	public T sample() {
		return datas[randomIndex()];
	}
	
	public void show() {
		StringBuilder sb = new StringBuilder();
		for (int i = headIndex ; i < headIndex + N ; i++) {
			sb.append(datas[i] + ",");
		}
		System.out.println(sb.toString());
	}
	
	private int randomIndex() {
		return headIndex + random.nextInt(N);
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		T[] temp = (T[]) new Object[capacity];
		System.arraycopy(datas, headIndex, temp, 0, N);
		this.datas = temp;
	}
	

	@Override
	public Iterator<T> iterator() {
		return new RandomQueueIterator(datas, headIndex, N);
	}
	
	private class RandomQueueIterator implements Iterator<T> {
		
		private T[] datas;
		private int N;
		
		private RandomQueueIterator(T[] datas, int headIndex, int size) {
			List<T> temp = new ArrayList<>();
			for (int i = headIndex ; i < headIndex + size ; i++) {
				temp.add(datas[i]);
			}
			Collections.shuffle(temp);
			this.datas = (T[]) temp.toArray(new Object[0]);
		}

		@Override
		public boolean hasNext() {
			return N < this.datas.length;
		}

		@Override
		public T next() {
			return datas[N++];
		}
		
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		RandomQueue<String> rq = new RandomQueue<>();
		for (int i = 0 ; i < 10 ; i++) {
			rq.enqueue(random.nextInt(100) + "");
		}
		rq.show();
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < 10 ; i++) {
			sb.append(rq.sample() + ",");
		}
		System.out.println("随机抽样: " + sb.toString());
		
		sb.setLength(0);
		for (String t : rq) {
			sb.append(t + ",");
		}
		System.out.println("随机迭代: " + sb.toString());
		
		sb.setLength(0);
		for (int i = 0 ; i < 10 ; i++) {
			sb.append(rq.dequeue() + ",");
		}
		System.out.println("随机出列: " + sb.toString());
	}
}
