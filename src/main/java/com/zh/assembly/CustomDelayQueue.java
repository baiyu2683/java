package com.zh.assembly;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 自定义的DelayQueue，通过设置延迟时间，单位，和消息以及消息的回调方法，实现定时消费消息
 * Created by zh on 2017-10-18.
 */
public class CustomDelayQueue {

    public static void main(String[] args) throws IOException {
        CustomDelayQueue customDelayQueue = CustomDelayQueue.customDelayQueueInstance;
        for(int i = 0; i < 10; i++) {
            customDelayQueue.addMessage(i, TimeUnit.SECONDS, "delay:" + i, d -> {
                System.out.println(d);
            });
        }
    }

    public static CustomDelayQueue customDelayQueueInstance = new CustomDelayQueue();
    private DelayQueue<Message> delayQueue = new DelayQueue<>();
    private CustomDelayQueue() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Message message = delayQueue.take();
                    if(message.consumer != null)
                        message.consumer.accept(message.data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.setName("MessageQueue");
        t.start();
    }

    public boolean addMessage(long delay, TimeUnit unit, Object v, Consumer consumer) {
        return delayQueue.add(new Message(delay, unit, v, consumer));
    }

    /**
     * DelayQueue 中使用的自定义Message
     */
    class Message implements Delayed {

        private long delay;
        private long nextTime;
        private Object data;
        private Consumer consumer;

        public Message(long delay, TimeUnit unit, Object data, Consumer consumer) {
            this.delay = delay;
            this.nextTime = TimeUnit.NANOSECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    + TimeUnit.NANOSECONDS.convert(delay, unit);
            this.data = data;
            this.consumer = consumer;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(nextTime - TimeUnit.NANOSECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long delay = getDelay(TimeUnit.NANOSECONDS);
            long otherDelay = o.getDelay(TimeUnit.NANOSECONDS);
            if(delay > otherDelay) return 1;
            if(delay < otherDelay) return -1;
            return 0;
        }

        public String toString() {
            Date date = new Date(TimeUnit.MILLISECONDS.convert(nextTime, TimeUnit.NANOSECONDS));
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            return "delay : " + delay + ", 触发时间:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        }
    }
}


