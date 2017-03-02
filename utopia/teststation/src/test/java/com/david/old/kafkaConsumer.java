package com.david.old;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConnector;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by David
 * on 2017/1/2
 */
public class kafkaConsumer extends Thread{

    private String topic;

    public kafkaConsumer(String topic){
        super();
        this.topic = topic;
    }


    @Override
    public void run() {
//        ConsumerConnector consumer = createConsumer();
//        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//        topicCountMap.put(topic, 1); // 一次从主题中获取一个数据
//        Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = consumer.createMessageStreams(topicCountMap);
//        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);// 获取每次接收到的这个数据
//        ConsumerIterator<byte[], byte[]> iterator =  stream.iterator();
//        while(iterator.hasNext()){
//            String message = new String(iterator.next().message());
//            System.out.println("接收到: " + message);
//        }
    }

    private ConsumerConnector createConsumer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181");//声明zk
        properties.put("group.id", "group1");// 必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据
        return Consumer.create(new kafka.consumer.ConsumerConfig(properties));
    }


    public static void main(String[] args) {
        new kafkaConsumer("local").start();// 使用kafka集群中创建好的主题 local

    }

}