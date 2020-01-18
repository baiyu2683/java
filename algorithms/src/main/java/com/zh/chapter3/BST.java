package com.zh.chapter3;

import java.util.LinkedList;
import java.util.Queue;

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

    public Node max() {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.right != null) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }

    public Node min() {
        return min(root);
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        Node n = node;
        while (n != null) {
            if (n.left != null) {
                n = n.left;
            } else {
                break;
            }
        }
        return n;
    }

    /**
     * 向下取整，取小于等于node的最大值
     */
    public Node floor(Key key) {
        return floor(root, key);
    }

    /**
     * 思路:
     * 1. 如果key小于根节点，则小于等于key的节点肯定在根节点的左子树中
     * 2. 如果key等于根节点，则结果就是根节点
     * 3. 如果key大于根节点，
     *    3.1 如果根节点的右子树中存在小于等于key的节点值，则返回满足条件的节点
     *    3.2 如果根节点的右子树中不存在小于等于key的节点值，则说明根节点就是满足条件的节点
     * @param node
     * @param key
     * @return
     */
    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return floor(node.left, key);
        }
        Node n = floor(node.right, key);
        return n == null ? node : n;
    }

    public Node ceiling(Key key) {
        return null;
    }

    /**
     * 和floor思路相同
     * @param node
     * @param key
     * @return
     */
    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare > 0) {
            return ceiling(node.right, key);
        }
        Node n = ceiling(node.left, key);
        return n == null ? node : n;
    }


    /**
     * 返回树中有k个元素小于它的键
     * @param node
     * @param key
     * @return
     */
    public Node select(Node node, int key) {
        if (node == null) {
            return null;
        }
        int t = size(node.left);
        if (t == key) {
            return node;
        } else if (t > key) {
            return select(node.left, key);
        } else {
            return select(node.right, key - t - 1);
        }
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    /**
     * 返回比key小的元素的个数
     * @param key
     * @param x
     * @return
     */
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int compare = key.compareTo(x.key);
        if (compare == 0) {
            return size(x.left);
        } else if (compare < 0) {
            return size(x.left) + 1 + rank(key, x.right);
        } else {
            return rank(key, x.left);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 递归删除最小节点
     * @param x
     * @return 删除最小节点后的根节点
     */
    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node delete(Key key) {
        return delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = delete(node.left, key);
        } else if (compare > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node t = node;
            node = min(t);
            node.left = t.left;
            node.right = deleteMin(t.right);

        }
        return null;
    }

    public Iterable<Key> keys() {
        return keys(min().key, max().key);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * 中序遍历
     * @param x
     * @param queue
     * @param lo
     * @param hi
     */
    public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int comparelo = lo.compareTo(x.key);
        int comparehi = hi.compareTo(x.key);
        if (comparelo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (comparelo <= 0 && comparehi >= 0) {
            queue.add(x.key);
        }
        if (comparehi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }
}
