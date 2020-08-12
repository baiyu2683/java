package com.zh.chapter4.topological;

import com.zh.chapter4.digraph.DiGraph;

import java.util.Stack;

/**
 * 检测图中的有向环
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;

    /**
     * 有向环中的所有顶点(如果存在)
     */
    private Stack<Integer> cycle;
    /**
     * 递归调用的宅上的所有顶点
     */
    private boolean[] onStack;

    public DirectedCycle(DiGraph graph) {
        onStack = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        for (int v = 0 ; v < graph.V() ; v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(DiGraph graph, int v) {
        // 以v为起点的栈开始记录路径
        onStack[v] = true;

        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (this.hasCycle()) {
                return;
            }
            // 没标记过的，进行深度优先搜索
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            } else if (onStack[w]) {
                // 如果节点已经标记过并且在环上，
                cycle = new Stack<>();
                // 说明v可以抵达w
                for (int x = v ; x != w ; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        // 以v为起点的栈路径记录完毕
        onStack[v] = false;
    }


    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        DiGraph diGraph = new DiGraph(6);
        diGraph.addEdge(0, 5);
        diGraph.addEdge(3, 5);
        diGraph.addEdge(4, 3);
        diGraph.addEdge(5, 4);

        DirectedCycle directedCycle = new DirectedCycle(diGraph);
        System.out.println(directedCycle.hasCycle());
        Iterable<Integer> cycle = directedCycle.cycle();
        if (cycle != null) {
            for (Integer i : cycle) {
                System.out.println(i);
            }
        }
    }
}
