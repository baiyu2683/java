package com.zh.chapter4.topological;

import com.zh.chapter4.digraph.DiGraph;

/**
 * 拓扑排序
 * 当且仅当一副有向图是无环图时，他才能进行拓扑排序
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(DiGraph G) {
        // 无环
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    // 是否是有向无环图
    public boolean isDAG() {
        return order != null;
    }

    Iterable<Integer> order() {
        return order;
    }
}
