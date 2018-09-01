package com.zh.mappers;

import com.zh.domain.User;

public interface UserMapper {
    
    User selectById(int id);
    
    Integer save(User user);
}
