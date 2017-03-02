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
public class MyKafkaProducer1 extends Thread {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.134.128:9093,192.168.134.128:9094,192.168.134.128:9095");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));

        producer.close();
//        /**
//         * 创建Produer连接参数
//         */
//        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers", "192.168.134.128:9093,192.168.134.128:9094,192.168.134.128:9095");
////        properties.setProperty("bootstrap.servers", "192.168.134.128:9092");
//        properties.setProperty("key.serializer", StringSerializer.class.getName());
//        properties.setProperty("value.serializer", StringSerializer.class.getName());
//        /**
//         * 构造消息Producer
//         */
//        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
//        /**
//         * 构造消息体
//         */
//        ProducerRecord<String, String> record = new ProducerRecord<String, String>("TestTopic", "10010", "wanghh true 24");
//        producer.send(record);
////        producer.flush();
////        producer.close();
    }
}
