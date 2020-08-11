package com.zh.chapter4;

import com.zh.chapter4.graph.Graph;

/**
 * 二分图, 着色
 */
public class TwoColor {

    public boolean[] marked;
    public boolean[] color;
    private boolean isTwoColor = true;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];
        for (int s = 0 ; s < graph.V() ; s++) {
            if (!marked[s]) {
                dfs(graph, s);
            }
        }
    }

    public void dfs(Graph graph, int s) {
        marked[s] = true;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                color[w] = !color[s];
                dfs(graph, w);
            } else {
                if (color[w] == color[s]) {
                    isTwoColor = false;
                }
            }
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
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

        TwoColor twoColor = new TwoColor(graph);
        System.out.println(twoColor.isTwoColor());
    }
}
