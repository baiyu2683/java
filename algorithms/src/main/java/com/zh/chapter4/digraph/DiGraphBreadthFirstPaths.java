package com.zh.chapter4.digraph;

import com.zh.chapter1.chapter13.Queue;

import java.util.LinkedList;

/**
 * 广度优先搜索搜索路径
 */
public class DiGraphBreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DiGraphBreadthFirstPaths(DiGraph diGraph, int s) {
        this.marked = new boolean[diGraph.V()];
        this.edgeTo = new int[diGraph.V()];
        this.s = s;
        bfs(diGraph, s);
    }

    private void bfs(DiGraph diGraph, int v) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(v);
        marked[v] = true;
        while (!queue.isEmpty()) {
            Integer w = queue.pop();
            for (Integer d : diGraph.adj(w)) {
                if (!marked[d]) {
                    edgeTo[d] = w;
                    marked[d] = true;
                    queue.enqueue(d);
                 }
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
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = v ; i != s ; i = edgeTo[i]) {
            linkedList.addFirst(i);
        }
        linkedList.addFirst(s);
        StringBuilder paths = new StringBuilder();
        for (Integer i : linkedList) {
            paths.append(i).append("-");
        }
        return paths.substring(0, paths.length() - 1);
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

        DiGraphBreadthFirstPaths diGraphBreadthFirstPaths = new DiGraphBreadthFirstPaths(diGraph, 0);
        System.out.println(diGraphBreadthFirstPaths.pathTo(4));
    }
}
