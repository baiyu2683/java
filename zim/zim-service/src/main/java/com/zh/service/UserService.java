package com.zh.service;

import com.zh.entity.UserPO;
import com.zh.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/06
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void listAll() {
        List<UserPO> userPOList = userMapper.listAll();
        System.out.println(userPOList);
    }
}
