package com.zh.mapper;

import com.zh.entity.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/06
 */
public interface UserMapper {

    List<UserPO> list(@Param("page") int page, @Param("pageSize") int pageSize);

    UserPO getByCode(@Param("code") String code);

    void updateByCode(UserPO userPO);
}
