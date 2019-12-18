package com.zh.message;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangheng
 * @date 2019/12/17
 */
@Configuration
public class MessageConfiguration {

    @Bean
    public Queue queue() {
        return new Queue("message");
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding binding(@Autowired Queue queue, @Autowired TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
    }
}
