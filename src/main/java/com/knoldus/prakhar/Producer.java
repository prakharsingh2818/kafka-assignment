package com.knoldus.prakhar;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.prakhar.Serialization");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<>(properties);

        try {
            for (int i = 1; i <= 10; i++) {
                User user = new User(i,"Prakhar",25,"Btech");
                kafkaProducer.send(new ProducerRecord("prakhar-topic", String.valueOf(user.getId()), user));
                //System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}

