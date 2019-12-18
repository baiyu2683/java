package com.zh.service;

import com.zh.cache.constant.Consts;
import com.zh.entity.UserPO;
import com.zh.mapper.UserMapper;
import com.zh.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(key = "#root.methodName + ':' + #page + ':' + #pageSize")
    @Transactional()
    public List<UserDTO> list(int page, int pageSize) {
        List<UserPO> userPOList = userMapper.list(page - 1, pageSize);
        List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO.Builder builder = UserDTO.newBuilder();
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

    @Override
    @Cacheable(key = "#code")
    public UserDTO getByCode(String code) {
        UserPO userPO = userMapper.getByCode(code);
        return convert(userPO);
    }

    private UserDTO convert(UserPO userPO) {
        UserDTO.Builder builder = UserDTO.newBuilder();
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

    @Override
    @CachePut(key = "#userPO.code")
    @Transactional
    public UserDTO updateByCode(UserDTO userDTO) {
        userDTO = userDTO.toBuilder().setModifyTime(System.currentTimeMillis()).build();
        UserPO userPO = new UserPO();
        userPO.setModifyTime(new Date(userDTO.getModifyTime()));
        userPO.setSex(userDTO.getSex());
        userPO.setNickname(userDTO.getNickName());
        userMapper.updateByCode(userPO);
        return getByCode(userPO.getCode());
    }

}
