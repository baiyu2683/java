package com.zh.chapter3;

import com.zh.leetcode.TreeNode;

/**
 * 红黑树
 */
public class RedBlackTree <Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Key key; // 键
        Value val; // 值
        Node left, right; // 指向子树的链接
        int N; // 以该节点为根的子树中的节点总数
        int color; // 0 红色 1 黑色
    }

    private boolean isRed(Node h) {
        return h != null && h.color == 0;
    }

    /**
     * 将节点h左旋转
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        if (h == null) {
            return null;
        }
        Node right = h.right;
        if (right == null) {
            return null;
        }
        h.right = right.left;
        right.color = h.color;
        right.left = h;
        right.color = h.color;
        h.color = 0;
        right.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return right;
    }

    /**
     * 将节点h右旋转
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        if (h == null) {
            return null;
        }
        Node left = h.left;
        if (left == null) {
            return null;
        }
        h.left = left.right;
        left.right = h;
        left.N = h.N;
        left.color = h.color;
        h.color = 0;
        h.N = 1 + size(h.left) + size(h.right);
        return left;
    }

    /**
     * 将节点两个子链接变色
     * @param h
     */
    private void colorFlips(Node h) {
        h.color = 0;
        h.left.color = 1;
        h.right.color = 1;
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + size(n.left) + size(n.right);
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = 1;
    }

    public Node put(Node h, Key key, Value value) {
        if (h == null) {
            h = new Node();
            // 每个新建的节点都是红色的
            h.color = 0;
            h.N = 1;
            return h;
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.val = value;
        }

        // 左黑右红 -> 左旋
        if (!isRed(h.left) && isRed(h.right)) {
            h = rotateLeft(h);
        }
        // 左子节点是红色的并且左子节点的左子节点也是红色的，右旋
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 如果两个子节点都是红色的，变色
        if (isRed(h.left) && isRed(h.right)) {
            colorFlips(h);
        }

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
}
