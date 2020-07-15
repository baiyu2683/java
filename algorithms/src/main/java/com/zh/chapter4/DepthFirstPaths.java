package com.zh.chapter4;

import com.zh.chapter1.chapter13.Stack;
import com.zh.chapter4.graph.Graph;

/**
 * 深度优先搜索实现寻路算法
 */
public class DepthFirstPaths implements Paths {

    // 这个顶点上是否调用过dfs了
    private boolean[] marked;

    // 从起点到一个顶点的已知路径上的最后一个顶点
    private int[] edgeTo;

    private final int s;

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s ; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
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

        Paths paths = new DepthFirstPaths(graph, 0);
        System.out.println(paths.hasPathTo(3));
        for (Integer v : paths.pathTo(3)) {
            System.out.print(v + " ");
        }
    }
}
