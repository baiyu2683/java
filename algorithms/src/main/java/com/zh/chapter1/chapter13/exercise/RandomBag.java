package com.zh.chapter1.chapter13.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 随即背包
 * @author zh
 * 2018年8月19日
 */
public class RandomBag<T> implements Iterable<T> {
	
	private T[] datas;
	private int N;
	
	public RandomBag(int capacity) {
		datas = (T[]) new Object[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void add(T t) {
		if (N == datas.length) {
			throw new RuntimeException("背包已满");
		}
		datas[N++] = t;
	}

	@Override
	public Iterator<T> iterator() {
		return new BagIterator(datas);
	}
	
	private class BagIterator implements Iterator<T> {
		
		private T[] datas;
		private int N;
		
		private BagIterator(T[] datas) {
			List<T> temp = new ArrayList<>(datas.length);
			for (T t : datas) {
				temp.add(t);
			}
			Collections.shuffle(temp);
			this.datas = (T[]) temp.toArray(new Object[0]);
		}

		@Override
		public boolean hasNext() {
			return N < datas.length;
		}

		@Override
		public T next() {
			return datas[N++];
		}
	}
	
	public static void main(String[] args) {
		RandomBag<String> rb = new RandomBag<>(10);
		for (int i = 0 ; i < 10 ; i++) {
			rb.add(String.valueOf(i));
		}
		for (String s : rb) {
			System.out.println(s);
		}
	}
}
