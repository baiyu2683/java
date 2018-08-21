package com.zh.chapter1.chapter13.exercise;

import java.util.Arrays;

/**
 * 可扩容数组实现的双向队列
 * @author zh
 * 2018年8月16日
 */
public class ResizingArrayQueue<T> implements Deque<T> {
	
	private T[] datas;
	private int headIndex;
	private int size;
	
	private int DEFAULT_SIZE = 1 << 3;
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pushLeft(T t) {
		if (size == 0) {
			resize(DEFAULT_SIZE);
			headIndex = DEFAULT_SIZE / 2;
			datas[--headIndex] = t;
		} else {
			if (headIndex <= 0) {
				int capacity = size * 2;
				// 两边各留size的二分之一，保持两边空位数量一致
				headIndex = capacity / 3;
				resize(capacity, headIndex);
			}
			datas[--headIndex] = t;
		}
		size++;
	}

	@Override
	public void pushRight(T t) {
		if (size == 0) {
			resize(DEFAULT_SIZE);
			headIndex = DEFAULT_SIZE / 2;
			datas[headIndex] = t;
		} else {
			if (headIndex + size >= datas.length) {
				int capacity = size * 2;
				// 两边各留size的二分之一，保持两边空位数量一致
				headIndex = capacity / 3;
				resize(capacity, headIndex);
			}
			datas[headIndex + size] = t;
		}
		size++;
	}

	@Override
	public T popLeft() {
		if (size <= 0) {
			return null;
		} else {
			T t = datas[headIndex];
			datas[headIndex++] = null;
			size--;
			return t;
		}
	}

	@Override
	public T popRight() {
		if (size <= 0) {
			return null;
		} else {
			int lastIndex = headIndex + size - 1;
			T t = datas[lastIndex];
			datas[lastIndex] = null;
			size--;
			return t;
		}
	}
	
	private void resize(int capacity) {
		resize(capacity, 0);
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity, int head) {
		T[] new_data = (T[]) new Object[capacity];
		if (datas != null) {
			System.arraycopy(datas, 0, new_data, head, size);
		}
		datas = new_data;
	}
	
	public String toString() {
		return datas == null ? "[]" : Arrays.toString(datas);
	}
}
