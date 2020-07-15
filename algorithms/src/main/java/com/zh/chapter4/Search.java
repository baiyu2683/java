package com.zh.chapter4;

/**
 *
 * 遍历算法
 * 图处理api: 找到和起点s连通的所有顶点
 *
 *
 * 将图的表示Graph和图的实现分离开来
 */
public interface Search {

    /**
     * v和起点s是否连通
     * @param v
     * @return
     */
    boolean marked(int v);

    /**
     * 与起点s连通的顶点总数
     * @return
     */
    int count();
}
