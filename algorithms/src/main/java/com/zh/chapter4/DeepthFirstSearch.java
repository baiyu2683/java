package com.zh.chapter4;

import com.zh.chapter4.graph.Graph;

/**
 * 深度优先搜索， 目的是找到顶点到起点是否有路径的问题
 * 相比之下广度优先搜索解决了最短路径的问题
 */
public class DeepthFirstSearch implements Search {

    private boolean[] marked;
    private int count;

    public DeepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        System.out.println("v: " + v);
        marked[v] = true;
        count++;
        // 深度优先搜索，filo，先进后出
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return this.marked[v];
    }

    @Override
    public int count() {
        return this.count;
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

        Search search = new DeepthFirstSearch(graph, 0);
        System.out.println(search.count());
        System.out.println(search.marked(3));

    }
}
