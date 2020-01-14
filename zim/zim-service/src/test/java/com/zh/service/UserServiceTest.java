package com.zh.service;

import com.zh.model.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author zhangheng
 * @date 2019/12/06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Reference
    private UserService userService;

    @Test
    public void list() {
        List<UserDTO> userDTOS = userService.list(1, 10);
        System.out.println(userDTOS.size());
    }

    @Test
    public void getByCode() {
        UserDTO userDTO = userService.getByCode("500000");
        System.out.println(userDTO.toString());
    }

    @Test
    public void updateByCode() {
        UserDTO userDTO = UserDTO.newBuilder()
                .setModifyTime(System.currentTimeMillis())
                .setSex(UserDTO.Sex.female)
                .setNickName("asdf")
                .build();

        userService.updateByCode(userDTO);
    }

    @Test
    public void deleteByCode() {

    }
}
