package com.zh.rabbitmq.tutorial.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 日志接受者
 */
public class ReceiveLogs {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        // 默认非持久化，断开连接自动删除，队列被一个客户端独占
        String quueName = channel.queueDeclare().getQueue();
        channel.queueBind(quueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "utf-8");
            System.out.println("[x] Received ‘" + message + "’");
        };
        channel.basicConsume(quueName, true, deliverCallback, consumerTag -> {
            System.out.println(consumerTag);
        });
    }
}
