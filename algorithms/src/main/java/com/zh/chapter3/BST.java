package com.zh.chapter3;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;


    private class Node {
        private Key key; // 键
        private Value val; // 值
        private Node left, right; // 指向子树的链接
        private int N; // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

//    public Value get(Key key) {
//        Node node = root;
//        while (node != null) {
//            int compare = node.key.compareTo(key);
//            if (compare == 0) {
//                return node.val;
//            } else if (compare > 0) {
//                node = node.left;
//            } else if (compare < 0) {
//                node = node.right;
//            }
//        }
//        return null;
//    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = node.key.compareTo(key);
        if (compare == 0) {
            return node.val;
        } else if (compare < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            node = new Node(key, value, 1);
            return node;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            node.val = value;
        } else if (cmp < 0) {
            return put(node.left, key, value);
        } else {
            return put(node.right, key, value);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
}
