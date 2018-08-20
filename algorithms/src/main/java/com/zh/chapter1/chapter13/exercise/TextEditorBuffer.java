package com.zh.chapter1.chapter13.exercise;

import java.util.Stack;

/**
 * 文本编辑器缓冲区
 * @author zh
 * 2018年8月20日
 */
public class TextEditorBuffer implements Buffer {
	
	Stack<String> front = new Stack<>(); // 光标前面的内容
	Stack<String> after = new Stack<>(); // 光标后面的内容
	
	public TextEditorBuffer() {
	}

	@Override
	public void insert(String c) {
		front.push(c);
	}

	@Override
	public String delete() {
		return front.pop();
	}

	@Override
	public void left(int k) {
		for (int i = 0 ; i < k ; i++) {
			if (front.isEmpty()) break;
			after.push(front.pop());
		}
	}

	@Override
	public void right(int k) {
		for (int i = 0 ; i < k ; i++) {
			if (after.isEmpty()) break;
			front.push(after.pop());
		}
	}

	@Override
	public int size() {
		return front.size() + after.size();
	}

	public static void main(String[] args) {
		TextEditorBuffer teb = new TextEditorBuffer();
		for (int i = 0 ; i < 10 ; i++) {
			teb.insert(String.valueOf(i));
		}
		teb.right(2);
		System.out.println(teb.delete()); // 9
		teb.left(2);
		System.out.println(teb.delete()); // 6
		System.out.println(teb.delete()); // 5
	}
}
