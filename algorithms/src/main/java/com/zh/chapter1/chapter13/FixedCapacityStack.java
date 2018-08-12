package com.zh.chapter1.chapter13;

import java.util.Iterator;

/**
 * 泛型定容栈, 并实现一个逆序迭代器
 * 
 * @author zh
 *
 */
@SuppressWarnings("unchecked")
public class FixedCapacityStack<T> implements Iterable<T> {

	private T[] a;
	private int N;
	
	public FixedCapacityStack(int n) {
		a = (T[]) new Object[n];
	}
	
	public void push(T t) {
		if (N == a.length) 
			resize(2 * a.length);
		a[N++] = t;
	}
	
	public T pop() {
		T t = a[--N];
		a[N] = null;
		// 元素个数小于四分之一的时候，就将数组大小改为原来的二分之一
		if (N > 0 && N == a.length / 4) resize(a.length / 2); 
		return t;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	/**
	 * 超出原数组大小时需要重新分配空间
	 * @param max
	 */
	private void resize(int max) {
		T[] temp = (T[]) new Object[max];
		System.arraycopy(a, 0, temp, 0, N);
		a = temp;
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

	
	public static void main(String[] args) {
		FixedCapacityStack<String> fcs = new FixedCapacityStack<>(1);
		fcs.push("1");
//		System.out.println(fcs.size());
		fcs.push("2");
//		System.out.println(fcs.size());
		fcs.push("3");
		fcs.push("4");
		fcs.push("5");
//		System.out.println(fcs.size());
		
		for (String s : fcs) {
			System.out.println(s);
		}
	}
}
