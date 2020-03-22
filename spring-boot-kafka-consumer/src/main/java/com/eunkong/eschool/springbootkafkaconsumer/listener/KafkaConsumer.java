package com.eunkong.eschool.springbootkafkaconsumer.listener;

import com.eunkong.eschool.springbootkafkaconsumer.config.KafkaConfig;
import com.eunkong.eschool.springbootkafkaconsumer.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private final String topicName = "Kafka_Example";

    @KafkaListener(topics = topicName, groupId = KafkaConfig.GROUP_ID)
    public void consume(String message) {
        log.info("Consume message: " + message);
    }

    @KafkaListener(topics = topicName, groupId = KafkaConfig.GROUP_JSON_ID, containerFactory = "userKafkaListenerContainerFactory")
    public void consumeUser(User user) {
        log.info("Consume user message: " + user);
    }
}
