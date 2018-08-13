package com.zh.chapter1.chapter13.exercise;

import com.zh.chapter1.chapter13.Stack;

/**
 * 判断括号是否配对完整
 * @author zh
 * 2018年8月13日
 */
public class Parentheses {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
//		String operators = "[()]{}{[()()]()}";
		String operators = "[(])";
		String[] ss = operators.split("");
		for (int i = 0 ; i < ss.length ; i++) {
			String s = ss[i];
			if ("[".equals(s) || "(".equals(s) || "{".equals((s))) {
				stack.push(s);
			} else {
				String top = stack.pop();
				if (!(("(".equals(top) && ")".equals(s)) ||
						("[".equals(top) && "]".equals(s)) ||
						("{".equals(top) && "}".equals(s)))) {
					System.out.println(false);
					break;
				}
			}
		}
		if (stack.size() > 0) {
			System.out.println(false);
		} else {
			System.out.println(true);
		}
	}
}
