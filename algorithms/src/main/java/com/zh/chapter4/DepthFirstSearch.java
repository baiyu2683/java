package com.zh.chapter4;

/**
 * 深度优先搜索判定一个点到另外一个点是否存在路径
 * 2019/9/17
 * zhangheng2683@gmail.com
 */
public class DepthFirstSearch {

    // 起始点
    private int v;
    // 与v连通的顶点数
    private int markedCount;
    // 深度有限搜索是否访问到
    private boolean[] marked;

    public DepthFirstSearch(Graph graph, int v) {
        this.v = v;
        this.marked = new boolean[graph.getNodeCount()];
        depth(graph, v);
    }

    private void depth(Graph graph, int v) {
        markedCount++;
        marked[v] = true;
        Iterable<Integer> nodes = graph.adj(v);
        for (Integer node : nodes) {
            if (!marked(node)) {
                depth(graph, node);
            }
        }
    }

    /**
     * w是否与v连通
     * @param w
     * @return
     */
    private boolean marked(int w) {
        return marked[w];
    }

    /**
     * 与v连通的顶点数目
     * @return
     */
    private int count() {
        return markedCount;
    }
}
