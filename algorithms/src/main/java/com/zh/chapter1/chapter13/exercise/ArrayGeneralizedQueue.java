package com.zh.chapter1.chapter13.exercise;

public class ArrayGeneralizedQueue<T> implements GeneralizedQueue<T>{
	
	private T[] datas;
	private int N;

	public ArrayGeneralizedQueue() {
		datas = (T[]) new Object[8];
	}
	
	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	@Override
	public void insert(T t) {
		if (N >= datas.length) {
			resize(N * 2);
		}
		datas[N++] = t;
	}

	@Override
	public T delete(int k) {
		if (k > N) {
			throw new ArrayIndexOutOfBoundsException("k 大于 " + (N - 1));
		}
		T t = datas[k];
		for (int i = k ; i < N - 1 ; i++) {
			datas[i] = datas[i+1];
		}
		datas[N - 1] = null;
		N--;
		return t;
	}
	
	private void resize(int capacity) {
		T[] temp = (T[]) new Object[capacity];
		System.arraycopy(datas, 0, temp, 0, N);
		datas = temp;
	}

	public static void main(String[] args) {
		GeneralizedQueue<Integer> queue = new ArrayGeneralizedQueue<>();
		for (int i = 0 ; i < 10 ; i++) {
			queue.insert(i);
		}
		System.out.println(queue.delete(4));
		System.out.println(queue);
	}
}
