package com.zh.message;

import com.zh.model.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author zhangheng
 * @date 2019/12/17
 */
@Component
@RabbitListener(queues = "message")
public class MessageReceiver {

    @RabbitHandler
    public void handleMessage(@Payload UserDTO message) {
        System.out.println(message);
    }

}
