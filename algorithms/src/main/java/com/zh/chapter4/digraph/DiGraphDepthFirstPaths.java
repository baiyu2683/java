package com.zh.chapter4.digraph;

import com.zh.chapter1.chapter13.exercise.DeQueue;

import java.util.LinkedList;

/**
 * 有向图深度优先搜索，找两点之间是否有路径
 */
public class DiGraphDepthFirstPaths {

    private boolean[] marked; // 遍历标记
    private int[] edgeTo; // 路径
    private int s; // 起点

    public DiGraphDepthFirstPaths(DiGraph diGraph, int s) {
        marked = new boolean[diGraph.V()];
        this.s = s;
        this.edgeTo = new int[diGraph.V()];
        dfs(diGraph, s);
    }

    private void dfs(DiGraph diGraph, int s) {
        marked[s] = true;
        for (Integer v : diGraph.adj(s)) {
            if (!marked[v]) {
                edgeTo[v] = s;
                dfs(diGraph, v);
            }
        }
    }

    public boolean hasPath(int v) {
        return marked[v];
    }

    public String pathTo(int v) {
        if (!hasPath(v)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int w = v ; w != s ; w = edgeTo[w]) {
            linkedList.addFirst(w);
        }
        linkedList.addFirst(s);
        for (int i = 0 ; i < linkedList.size() ; i++) {
            stringBuilder.append(linkedList.get(i)).append(",");
        }
        return stringBuilder.substring(0,stringBuilder.length() - 1);
    }

    public static void main(String[] args) {
        DiGraph diGraph = new DiGraph(5);
        diGraph.addEdge(0, 2);
        diGraph.addEdge(2, 4);
        diGraph.addEdge(1, 3);
        diGraph.addEdge(1, 2);
        diGraph.addEdge(2, 3);
        diGraph.addEdge(3, 1);
        diGraph.addEdge(4, 3);

//        System.out.println(diGraph.reverse());
        DiGraphDepthFirstPaths diGraphDepthFirstPaths = new DiGraphDepthFirstPaths(diGraph, 0);
        System.out.println(diGraphDepthFirstPaths.pathTo(4));
    }
}
