package com.zh.chapter4;

import com.zh.chapter4.graph.Graph;

/**
 * 使用深度优先搜索找出一幅图的所有连通分量
 *
 */
public class DepthFirstCC implements CC {

    private boolean[] marked; // 是否已经访问过了
    private int[] id; // 连通分量标识
    private int count; // 连通分量个数
    private Graph graph;

    public DepthFirstCC(Graph graph) {
        this.graph = graph;
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        // TODO 深度优先搜索确定连通分量
        // 按数字索引，最多有Graph.V()个连通分量, 按计数作为连通分量标识
        for (int i = 0 ; i < graph.V() ; i++) {
            if (!marked[i]) {
                dfs(graph, i, count);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v, int id) {
        marked[v] = true;
        this.id[v] = id;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w, id);
            }
        }
    }


    @Override
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(1, 0);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 3);
        graph.addEdge(4, 6);
        graph.addEdge(5, 0);
        graph.addEdge(5, 3);
        graph.addEdge(5, 4);
        graph.addEdge(6, 0);
        graph.addEdge(6, 4);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 12);
        graph.addEdge(10, 9);
        graph.addEdge(11, 9);
        graph.addEdge(11, 12);
        graph.addEdge( 12, 9);
        graph.addEdge(12, 11);

        DepthFirstCC depthFirstCC = new DepthFirstCC(graph);
        System.out.println(depthFirstCC.count());
        System.out.println(depthFirstCC.connected(0, 9));
        System.out.println(depthFirstCC.connected(0, 4));
        System.out.println(depthFirstCC.id(5));
        System.out.println(depthFirstCC.id(7));
    }
}
