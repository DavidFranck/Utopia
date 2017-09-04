package com.david.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by David
 * on 2017/1/3
 */
public class MyKafkaProducer extends Thread {

    private String topic;

    public MyKafkaProducer(String topic) {
        super();
        this.topic = topic;
    }

    @Override
    public void run() {
        Producer producer = createProducer();
        int i = 0;
        while (true) {
            /**
             * 构造消息体
             */
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("tes_topic", "10010", "wanghh true 24");
            producer.send(record);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i++);
//            producer.flush();
//            producer.close();
        }
    }

    private Producer createProducer() {
        /**
         * 创建Produer连接参数
         */
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        /**
         * 构造消息Producer
         */
        return new KafkaProducer<String, String>(properties);
    }

    public static void main(String[] args) {
        new MyKafkaProducer("test_topic").start();
    }

}
