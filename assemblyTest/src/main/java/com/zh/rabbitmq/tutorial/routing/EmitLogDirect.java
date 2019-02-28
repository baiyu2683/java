package com.zh.rabbitmq.tutorial.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String BINDINGKEY_ERROR = "error";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String message = "error msg";

            channel.basicPublish(EXCHANGE_NAME, BINDINGKEY_ERROR, null, message.getBytes("utf-8"));
            System.out.println(" [x] Sent ‘" + BINDINGKEY_ERROR + ":" + message + "’");
        }
    }
}
