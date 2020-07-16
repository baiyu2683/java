package com.zh.chapter4;

import com.zh.chapter4.graph.Graph;

import java.util.*;

/**
 *  广度优先搜索, 目的是找到最短的路径，而不仅仅是找到路径，这一点是和深度优先的区别
 *
 *  广度优先搜索所需的时间再最坏情况下和V+E成正比
 *  如果图是连通的，这个和就是所有顶点的度数之和
 */
public class BreadthFirstPaths implements Paths {

    private boolean[] marked; // 是否连通
    private int[] edgeTo; // 路径记录
    private final int s; // 起点

    public BreadthFirstPaths(Graph graph, int s) {
        this.s = s;
        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];
        bfs(graph, s);
    }

    private void bfs(Graph graph, int v) {
        marked[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int x : graph.adj(w)) { //
                if (!marked[x]) {   // 只检查未被标记的相邻顶点
                    edgeTo[x] = w; // 同时有多条满足条件的，取最后一条
                    marked[x] = true; // 标记为可达
                    queue.add(x); // 添加到队列中, fifo，和深度优先不同
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addFirst(v);
        for (int w = edgeTo[v] ; w != s ; w = edgeTo[w]) {
            queue.addFirst(w);
        }
        queue.addFirst(s);
        return queue;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 2);
        graph.addEdge(4, 3);
        graph.addEdge(4, 2);
        graph.addEdge(5, 3);
        graph.addEdge(5, 0);
//        graph.addEdge(5, 1);

        Paths paths = new BreadthFirstPaths(graph, 0);
        System.out.println(paths.hasPathTo(3));
        System.out.println(paths.pathTo(3));
    }
}
