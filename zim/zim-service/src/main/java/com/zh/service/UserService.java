package com.zh.service;

import com.zh.cache.constant.Consts;
import com.zh.entity.UserPO;
import com.zh.mapper.UserMapper;
import com.zh.model.UserDTOModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/06
 */
@Slf4j
@Service
@CacheConfig(cacheNames = Consts.KEY_USER)
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "#root.methodName + ':' + #page + ':' + #pageSize")
    public List<UserDTOModel.UserDTO> list(int page, int pageSize) {
        List<UserPO> userPOList = userMapper.list(page - 1, pageSize);
        List<UserDTOModel.UserDTO> userDTOS = new ArrayList<>();
        UserDTOModel.UserDTO.Builder builder = UserDTOModel.UserDTO.newBuilder();
        for (UserPO userPO : userPOList) {
            builder.clear();
            builder.setCode(userPO.getCode())
                    .setNickName(userPO.getCode())
                    .setSex(userPO.getSex());
            Date createTime = userPO.getCreateTime();
            if (createTime != null) {
                builder.setCreateTime(createTime.getTime());
            }
            Date modifyTime = userPO.getModifyTime();
            if (modifyTime != null) {
                builder.setModifyTime(createTime.getTime());
            }
            userDTOS.add(builder.build());
        }
        return userDTOS;
    }

    @Cacheable(key = "#code")
    public UserDTOModel.UserDTO getByCode(String code) {
        UserPO userPO = userMapper.getByCode(code);
        return convert(userPO);
    }

    private UserDTOModel.UserDTO convert(UserPO userPO) {
        UserDTOModel.UserDTO.Builder builder = UserDTOModel.UserDTO.newBuilder();
        builder.setCode(userPO.getCode())
                .setNickName(userPO.getCode())
                .setSex(userPO.getSex());
        Date createTime = userPO.getCreateTime();
        if (createTime != null) {
            builder.setCreateTime(createTime.getTime());
        }
        Date modifyTime = userPO.getModifyTime();
        if (modifyTime != null) {
            builder.setModifyTime(modifyTime.getTime());
        }
        return builder.build();
    }

    @CachePut(key = "#userPO.code")
    public UserDTOModel.UserDTO updateByCode(UserPO userPO) {
        if (userPO.getModifyTime() == null) {
            userPO.setModifyTime(new Date());
        }
        userMapper.updateByCode(userPO);
        return getByCode(userPO.getCode());
    }

}
