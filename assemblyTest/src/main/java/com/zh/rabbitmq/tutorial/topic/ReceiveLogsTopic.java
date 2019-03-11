package com.zh.rabbitmq.tutorial.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        String[] bindKeys = new String[]{"com.zh.*", "#.log"};
        for (String bindingKey : bindKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }

        System.out.printf("[*] Waiting for messages. To exis press CTRL+C");

        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "utf-8");
            System.out.printf("[x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        });
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            System.out.print(consumerTag);
        });
    }
}
