package com.zh.generics;

/**
 * Created by zh on 2017-05-17.
 */
public class LinkedList<T> {
    private static class Node<U> {
        U item;
        Node<U> next;
        Node() {
            item = null;
            next = null;
        }
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }
        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>();
    public void push(T item) {
        top = new Node<>(item, top);
    }
}
