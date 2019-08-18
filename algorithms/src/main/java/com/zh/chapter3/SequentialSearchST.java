package com.zh.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表实现符号表
 */
public class SequentialSearchST extends ST<String, String> {

    private Node first;

    @Override
    public String get(String key) {
        for (Node x = first ; x != null ; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        // 同名
        for (Node x = first ; x != null ; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        // 没有同名
        first = new Node(key, value, first);
    }

    @Override
    public void delete(String key) {
        if (first == null) {
            return;
        }
        Node node = new Node("", "", first);
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }

    @Override
    public boolean contains(String s) {
        return get(s) != null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        int count = 0;
        Node n = first;
        while (n != null) {
            count++;
            n = n.next;
        }
        return count;
    }

    @Override
    public Iterable<String> keys() {
        Node n = first;
        List<String> keys = new ArrayList<>();
        while (n != null) {
            keys.add(n.value);
            n = n.next;
        }
        return keys;
    }


    private class Node {
        String key;
        String value;
        Node next;

        Node(String key, String value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SequentialSearchST sequentialSearchST = new SequentialSearchST();
        sequentialSearchST.put("1", "asdf");
        sequentialSearchST.put("2", "xzcg");
        sequentialSearchST.put("3", "ewr");
        sequentialSearchST.put("4", "9234");
        System.out.println(sequentialSearchST.contains("3"));
        System.out.println(sequentialSearchST.size());
        System.out.println(sequentialSearchST.get("3"));
        Iterable<String> iterable = sequentialSearchST.keys();
        for (String key : iterable) {
            System.out.print(key + "-");
        }
        System.out.println();
        System.out.println("==============");
        sequentialSearchST.delete("3");
        System.out.println(sequentialSearchST.contains("3"));
        System.out.println(sequentialSearchST.get("3"));
        System.out.println(sequentialSearchST.size());
        Iterable<String> iterable2 = sequentialSearchST.keys();
        for (String key : iterable2) {
            System.out.print(key + "-");
        }
        System.out.println();
        System.out.println("==============");
    }
}
