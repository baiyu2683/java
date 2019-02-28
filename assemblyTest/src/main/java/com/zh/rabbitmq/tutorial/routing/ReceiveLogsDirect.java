package com.zh.rabbitmq.tutorial.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String BINDINGKEY_ERROR = "error";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, BINDINGKEY_ERROR);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "utf-8");
            System.out.println("[x] Receiver ‘" +
                    delivery.getEnvelope().getRoutingKey() + ":" + message + "’");
        });
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
