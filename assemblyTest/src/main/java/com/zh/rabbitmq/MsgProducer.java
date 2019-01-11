package com.zh.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.*;

public class MsgProducer {

    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        for (int i = 0 ; i < 10 ; i++) {
            ((ScheduledExecutorService) executorService).scheduleWithFixedDelay(() -> {
                try {
                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                    String message1 = "Hello RabbitMQ," + Thread.currentThread().getName();
                    channel.basicPublish("", QUEUE_NAME, null, message1.getBytes("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
    }
}
