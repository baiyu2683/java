package com.zh.chapter1.chapter13;

import java.util.Stack;

/**
 * 双栈法计算表达式的值
 * @Author zh2683
 */
public class Evaluate {

    public static void main(String[] args) {
        String expression = "( 1 + ( ( 2 * 300 ) / ( 5 + 95 ) ) )";
        String[] subStr = expression.split("\\s+");

        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        for (String sub : subStr) {
            if (sub.equals("(")); // 忽略左括号
            else if (sub.equals("+")) ops.push(sub); // 操作符如操作符栈
            else if (sub.equals("-")) ops.push(sub);
            else if (sub.equals("*")) ops.push(sub);
            else if (sub.equals("/")) ops.push(sub);
            else if (sub.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            else vals.push(Double.parseDouble(sub)); // 操作数如操作数栈
            System.out.println(vals);
        }
        System.out.println(vals.pop());
    }
}
