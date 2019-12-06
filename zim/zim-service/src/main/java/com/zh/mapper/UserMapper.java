package com.zh.mapper;

import com.zh.entity.UserPO;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/06
 */
public interface UserMapper {

    List<UserPO> listAll();
}
