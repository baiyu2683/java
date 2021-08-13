package com.zh.consumer;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class UnPointConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.8:9092");

        //自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 自动提交延迟
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 500);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // 消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "UnPointTestGroup");
        // 重置消费者offset到最开始
        // 如果需要重新消费，首先换新的GROUP_ID_CONFIG，然后设置AUTO_OFFSET_RESET_CONFIG为earliest
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 订阅主题,可以订阅多个组
        kafkaConsumer.subscribe(Collections.singleton("UnPoint"));

        while (true) {
            try {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.key() + "-" + consumerRecord.value());
                }
                // 手动提交ack
                kafkaConsumer.commitSync();
                System.out.println("下一轮" + System.currentTimeMillis());
            } catch (Exception e) {
                kafkaConsumer.close();
                throw e;
            }
        }
    }
}
