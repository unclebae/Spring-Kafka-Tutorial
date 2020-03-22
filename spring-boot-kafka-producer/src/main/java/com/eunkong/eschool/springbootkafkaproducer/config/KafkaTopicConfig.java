package com.eunkong.eschool.springbootkafkaproducer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * 이 클래스는 카프카 Topic 을 Spring 이 올라갈때 자동으로 생성한다.
 */
@Configuration
public class KafkaTopicConfig {
    @Value("${kafka.topic}")
    private String topicName;

    @Value(value = "${kafka.address}")
    private String kafkaAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(topicName, 3, (short) 3);
//        return new NewTopic("Kafka_Example", 1, (short) 1);
    }
}
