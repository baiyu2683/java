package com.zh.chapter4.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 无向图存储结构
 *
 * 使用邻接表结构
 * -----
 * | 1 | -> |2| -> ...
 * -----
 * | 2 | -> |3| -> ...
 * -----
 *   |
 */
public class Graph {

    // 顶点数目
    private final int V;
    // 边的数目
    private int E;
    // 邻接表
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList[V];
        for (int v = 0 ; v < this.V ; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        //TODO 边
        return this.E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        this.E++;
    }

    /**
     * 和v相邻的所有顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(V).append(" vertices, ").append(E).append(" edges\n");
        for (int v = 0 ; v < V ; v++) {
            stringBuilder.append(v).append(": ");
            for (int w : this.adj[v]) {
                stringBuilder.append(w).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
