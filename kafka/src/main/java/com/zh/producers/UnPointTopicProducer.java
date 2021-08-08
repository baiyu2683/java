package com.zh.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zh.entity.Point;
import org.apache.kafka.clients.producer.KafkaProducer;
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
public class UnPointTopicProducer implements Runnable {

    private static Properties config;

    static {
        config = new Properties();
        config.put("bootstrap.servers", "127.0.0.1:9092");
        config.put("transactional.id", "unPointTopicProducer-transactional-id");
    }

    @Override
    public void run() {
        JsonMapper jsonMapper = new JsonMapper();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config, new StringSerializer(), new StringSerializer());
        producer.initTransactions();
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.beginTransaction();
            try {
                Point point = new Point();
                point.setKey(UUID.randomUUID().toString());
                point.setRootUrl("http://www.baidu.com");
                point.setConfig("配置");
                producer.send(new ProducerRecord<>("UnPointTopic", point.getKey(), jsonMapper.writeValueAsString(point)));
                producer.commitTransaction();
                System.out.println("写入成功: " + point.getKey());
            } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
                 // We can't recover from these exceptions, so our only option is to close the producer and exit.
                 producer.close();
            } catch (KafkaException | JsonProcessingException e) {
                // For all other exceptions, just abort the transaction and try again.
                producer.abortTransaction();
            }
//            producer.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new UnPointTopicProducer());
        thread.start();
        new CountDownLatch(1).await();
    }
}
