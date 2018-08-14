package com.zh.chapter1.chapter13.exercise;

/**
 * 可自动扩容的字符串队列
 * @author zh
 * 2018年8月14日
 */
public class ResizingArrayQueueOfStrings {

	private String[] items = new String[0];
	private int headIndex;
	private int length;
	
	public void enqueue(String item) {
		if ((headIndex + length) == items.length) resize((headIndex + length) * 2);
		items[headIndex + length] = item;
		length++;
	}
	
	public String dequeue() {
		String item = items[headIndex++];
		length--;
		return item;
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	public int size() {
		return length;
	}
	
	private void resize(int capacity) {
		if (items.length == 0) {
			items = new String[1];
		} else {
			String[] temp = new String[capacity];
			System.arraycopy(items, headIndex, temp, 0, length);
			items = temp;
		}
	}
	
	public static void main(String[] args) {
		ResizingArrayQueueOfStrings raqs = new ResizingArrayQueueOfStrings();
		raqs.enqueue("1");
		raqs.enqueue("2");
		raqs.enqueue("3");
		raqs.enqueue("4");
		System.out.println(raqs.size());
		System.out.println(raqs.isEmpty());
		System.out.println(raqs.dequeue());
		System.out.println(raqs.dequeue());
		System.out.println(raqs.dequeue());
		System.out.println(raqs.dequeue());
		System.out.println(raqs.size());
		System.out.println(raqs.isEmpty());
	}
}
