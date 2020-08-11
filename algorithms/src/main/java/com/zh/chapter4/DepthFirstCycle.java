package com.zh.chapter4;

import com.zh.chapter4.graph.Graph;

/**
 * 深度优先搜素哦检测图是否是无环图
 */
public class DepthFirstCycle {

    private boolean[] marked;
    private boolean hasCycle;

    public DepthFirstCycle(Graph graph) {
        this.marked = new boolean[graph.V()];
        for (int s = 0 ; s < graph.V() ; s++) {
            dfs(graph, s, s);
        }
    }

    /**
     * v， 下一个要搜索的节点
     * v的上一个节点
     * 和v相邻的所有顶点，处理遍历路径前的那个节点，如果有标记过的，则说明有环。
     * @param graph
     * @param v
     * @param u
     */
    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w, v);
            } else {
                if (w != u) { // 遍历路径 u -> (v. v1, v2) - (u, u1, u2),会重复遍历 如果w == u，说名已经遍历过了无需处理，如果不等于说明有环
                    hasCycle = true;
                }
            }
        }
    }

    public boolean isHasCycle() {
        return this.hasCycle;
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

        DepthFirstCycle depthFirstCC = new DepthFirstCycle(graph);
        System.out.println(depthFirstCC.isHasCycle());
    }
}
