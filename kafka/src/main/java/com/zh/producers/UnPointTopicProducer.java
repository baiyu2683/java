package com.zh.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zh.entity.Point;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 未采集过的采集点重新采集
 */
public class UnPointTopicProducer {

    private static Properties config;

    static {
        config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.8:9092");
//        config.put("transactional.id", "unPointTopicProducer-transactional-id");
        // ack级别
        config.put(ProducerConfig.ACKS_CONFIG, "-1");
        config.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 批次大小，单位字节
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 等待时间，超时没有达到批次大小也会发送
        config.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // RecordAccumulator缓冲区大小
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 序列化,没有默认值，需要指定
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static void main(String[] args) throws InterruptedException {
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(config);
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("第" + (i+1) + "条");
                kafkaProducer.send(new ProducerRecord<>("UnPoint", "point-" + i));
            }
            System.out.println("结束");
            kafkaProducer.flush();
            System.out.println("flush结束");
        } finally {
            kafkaProducer.close();
            System.out.println("close");
        }
    }
}