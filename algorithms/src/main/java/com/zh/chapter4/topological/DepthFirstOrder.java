package com.zh.chapter4.topological;

import com.zh.chapter4.digraph.DiGraph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 基于深度优先搜索得顶点排序
 * 前序: 在递归调用之前将顶点加入队列
 * 后序：再递归调用之后将顶点加入队列
 * 逆后序：在递归调用之后将顶点压入栈
 */
public class DepthFirstOrder {

    private boolean[] marked;
    // 前序
    private Queue<Integer> pre;
    // 后序
    private Queue<Integer> post;
    //逆后序
    private Stack<Integer> reversePost;

    public DepthFirstOrder(DiGraph G) {
        pre = new ArrayDeque<>();
        post = new ArrayDeque<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for (int v = 0 ; v < G.V() ; v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        pre.add(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }
    public Iterable<Integer> post() {
        return post;
    }
    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        DiGraph diGraph = new DiGraph(13);
        diGraph.addEdge(0, 1);
        diGraph.addEdge(0, 6);
        diGraph.addEdge(0, 5);
        diGraph.addEdge(1, 5);
        diGraph.addEdge(2, 0);
        diGraph.addEdge(2, 3);
        diGraph.addEdge(3, 5);
        diGraph.addEdge(5, 4);
        diGraph.addEdge(6, 4);
        diGraph.addEdge(6, 9);
        diGraph.addEdge(7, 6);
        diGraph.addEdge(8, 7);
        diGraph.addEdge(9, 12);
        diGraph.addEdge(9, 11);
        diGraph.addEdge(9, 10);
        diGraph.addEdge(11, 12);


        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(diGraph);
        for (Integer i : depthFirstOrder.pre()) {
            System.out.print(i + ", ");
        }
        System.out.println();
        for (Integer i : depthFirstOrder.post()) {
            System.out.print(i + ", ");
        }
        System.out.println();
        Queue<Integer> post = depthFirstOrder.post;
        while (!post.isEmpty()) {
            System.out.print(post.poll() + ", ");
        }
        System.out.println();
        Stack<Integer> integers = depthFirstOrder.reversePost;
        while (!integers.empty()) {
            System.out.print(integers.pop() + ", ");
        }
        System.out.println();
    }
}
