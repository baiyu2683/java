package com.zh.chapter1.chapter13.exercise;

/**
 * 文本编辑器缓冲区
 * 提示: 使用两个栈
 * @author zh
 * 2018年8月19日
 */
public interface Buffer {
	
	/**
	 * 插入一个字符
	 * @param c
	 */
	void insert(String c);
	
	/**
	 * 删除一个字符
	 * @return
	 */
	String delete();
	
	/**
	 * 将光标向左移动k个位置
	 * @param k
	 */
	void left(int k);
	
	/**
	 * 将光标向右移动k个位置
	 * @param k
	 */
	void right(int k);
	
	/**
	 * 缓冲区中的字符数量
	 * @return
	 */
	int size();
}
