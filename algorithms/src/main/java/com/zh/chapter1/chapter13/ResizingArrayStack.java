package com.zh.chapter1.chapter13;

import java.util.Iterator;

/**
 * 容量可变的下压栈
 * @author zh
 * 2018年8月12日
 */
public class ResizingArrayStack<T> implements Iterable<T> {

	private T[] a = (T[]) new Object[1];
	
	private int N = 0;
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		 return N;
	}
	
	private void resize(int capacity) {
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0 ; i < N ; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(T t) {
		if (N == a.length) resize(2 * a.length);
		a[N++] = t;
	}
	
	public T pop() {
		T t = a[--N];
		a[N] = null;
		if (N > 0 && N == a.length / 4) resize(a.length / 2);
		return t;
	}

	@Override
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}
	
	/**
	 * 逆序迭代器
	 * @author zh
	 */
	private class ReverseArrayIterator implements Iterator<T>{

		private int i = N;
				
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public T next() {
			return a[--i];
		}
		
		@Override
		public void remove() {}
	}
}
