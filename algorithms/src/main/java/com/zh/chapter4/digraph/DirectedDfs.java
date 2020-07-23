package com.zh.chapter4.digraph;

/**
 * 有向图可达性api
 */
public class DirectedDfs {

    private boolean[] marked;
    private DiGraph diGraph;

    public DirectedDfs(DiGraph diGraph, int s) {
        marked = new boolean[diGraph.V()];
        dfs(diGraph, s);
    }

    private void dfs(DiGraph diGraph, int v) {
        marked[v] = true;
        for (Integer w : diGraph.adj(v)) {
            if (!marked[w]) {
                dfs(diGraph, w);
            }
        }
    }

    /**
     * 从s到v可达吗?
     * @param v
     * @return
     */
    private boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {

    }
}
