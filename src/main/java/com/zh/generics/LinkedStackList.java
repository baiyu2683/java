package com.zh.generics;

/**
 * Created by zh on 2017-05-17.
 */
public class LinkedStackList<T> {
    //非静态内部类的话可以访问外部类的类型参数
    private class Node {
        T item;
        Node next;
        Node() {
            item = null;
            next = null;
        }
        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
        boolean end() {
            return item == null && next == null;
        }
    }

    private Node top = new Node();
    public void push(T item) {
        top = new Node(item, top);
    }
    public T pop() {
        T result = top.item;
        if(!top.end())
            top = top.next;
        return result;
    }

    public static void main(String[] args) {
        LinkedStackList<String> lss = new LinkedStackList<>();
        for (String s : "Phasers or stun!".split(""))
            lss.push(s);
        String s;
        while ((s = lss.pop()) != null)
            System.out.println(s);
    }
}
