package com.zh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zh.domain.User;
import com.zh.mappers.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Transactional
    public Integer save() {
        User user = new User();
        user.setId(10);
        user.setName("1");
        user.setAddress("1");
        user.setAge(10);
        user.setSex(1);
        userMapper.save(user);
        return 1;
    }
}
