package com.david.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by David
 * on 2017/1/3
 */
public class MyKafkaConsumer {
    public static void main(String[] args) {
        int i = 0;
       /*创建Consummer必要的链接参数*/
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.134.128:9093,192.168.134.128:9094,192.168.134.128:9095");
          /*Kafka的消息消费是通过group管理，必填*/
        props.put("group.id", "group_1");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
             /*Kafka的消息消费者*/
        KafkaConsumer<String, Long> kafkaConsumer =
                new KafkaConsumer<String, Long>(props);
        /*Kafka的消息消费者订阅消息TestTopic*/
        kafkaConsumer.subscribe(Arrays.asList("TestTopic"));
        while (true) {
            System.out.println(i++);
             /*消费Testopic中的消息*/
            ConsumerRecords<String, Long> records =
                    kafkaConsumer.poll(1000);
            Iterator<ConsumerRecord<String, Long>> iterators =
                    records.iterator();
            while (iterators.hasNext()) {
                ConsumerRecord<String, Long> next =
                        iterators.next();
                System.out.println(next.key() + " " + next.value());
            }
        }
    }
}
