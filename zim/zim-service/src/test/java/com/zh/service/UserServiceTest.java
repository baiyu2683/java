package com.zh.service;

import com.zh.entity.UserPO;
import com.zh.model.UserDTOModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


/**
 * @author zhangheng
 * @date 2019/12/06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void list() {
        List<UserDTOModel.UserDTO> userDTOS = userService.list(1, 10);
        System.out.println(userDTOS.size());
    }

    @Test
    public void getByCode() {
        UserDTOModel.UserDTO userDTO = userService.getByCode("500000");
        System.out.println(userDTO.toString());
    }

    @Test
    public void updateByCode() {
        UserPO userPO = new UserPO();
        userPO.setCode("500000");
        userPO.setModifyTime(new Date());
        userPO.setNickname("123123123123");
        userPO.setSex(UserDTOModel.Sex.female);
        userService.updateByCode(userPO);
    }

    @Test
    public void deleteByCode() {

    }
}
