package com.zh.dao;

import java.util.List;

/**
 * 对象操作, T 为对象类型，S为id类型
 * Author: Administrator <br/>
 * Date: 2018-07-18 <br/>
 */
public interface BaseDao<T, S> {

    /**
     * 查询所有
     * @return
     */
    List<T> getAll();
    
    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    T getById(S id);
    
    /**
     * 添加一个对象
     * @param t
     * @return
     */
    T add(T t);
    
    /**
     * 根据id删除
     * @param id
     * @return
     */
    int remove(int id); 
    
    /**
     * 更新一个对象
     * @param t
     * @return
     */
    int update(T t);
}
