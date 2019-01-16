package com.zh.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class MsgConsumer {

    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("customer waiting received messages");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body, "utf-8");
                System.out.println("received:" + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
