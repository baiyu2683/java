package com.zh.message;

import com.zh.model.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;


/**
 * @author zhangheng
 * @date 2019/12/06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void send() throws InterruptedException {
        UserDTO userDTO = UserDTO.newBuilder().build();
        for (int i = 0 ; i < 1001 ; i++) {
            rabbitTemplate.convertAndSend("message", userDTO);
        }
    }
}
