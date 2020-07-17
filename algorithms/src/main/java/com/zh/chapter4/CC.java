package com.zh.chapter4;

/**
 * 寻找连通分量算法
 */
public interface CC {

    boolean connected(int v, int w); // v和w连通吗

    int count(); // 连通分量数

    int id(int v); // v所在的连通分量的标识符(0 ~ count() - 1)

}
