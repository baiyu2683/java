package com.zh.service;

import com.zh.model.UserDTO;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/16
 */
public interface UserService {

    List<UserDTO> list(int page, int pageSize);

    UserDTO getByCode(String code);

    UserDTO updateByCode(UserDTO userDTO);
}
