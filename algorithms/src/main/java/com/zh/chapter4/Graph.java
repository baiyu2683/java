package com.zh.chapter4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 无向图
 *
 * 2019/9/17
 * zhangheng2683@gmail.com
 */
public class Graph {

    // 顶点数目
    private int nodeCount;
    // 边数目
    private int edgeCount;
    // 邻接表
    List<Integer>[] adj;

    public void Graph(int nodeCount) {
        adj = new ArrayList[nodeCount];
        for (int i = 0 ; i < nodeCount ; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        edgeCount++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int getNodeCount() {
        return nodeCount;
    }

}
