package com.zh.chapter4.digraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 有向图
 */
public class DiGraph {

    private Integer V;

    private Integer E;

    private List<Integer>[] adj;

    /**
     * 创建一副含有V个顶点但没有边的有向图
     * @param V
     */
    public DiGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];
        for (int i = 0 ; i < V ; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    /**
     * 添加一条边v -> w
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        this.E++;
    }

    /**
     * 由v指出的边所连接的所有顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 该图的反向图
     * @return
     */
    public DiGraph reverse() {
        DiGraph reverse = new DiGraph(this.V);
        for (int v = 0 ; v < V ; v++) {
            Iterable<Integer> vAdj = adj[v];
            for (Integer w : vAdj) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int v = 0 ; v < this.V ; v++) {
            stringBuilder.append(v).append(":");
            for (Integer w : adj[v]) {
                stringBuilder.append(w).append(",");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
