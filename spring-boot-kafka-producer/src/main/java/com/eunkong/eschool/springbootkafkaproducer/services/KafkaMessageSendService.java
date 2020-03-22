package com.eunkong.eschool.springbootkafkaproducer.services;

import com.eunkong.eschool.springbootkafkaproducer.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaMessageSendService {

    @Value("${kafka.topic}")
    private String topicName;

    @Autowired
    @Qualifier("stringKafkaTemplate")
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    @Qualifier("userKafkaTemplate")
    KafkaTemplate<String, User> userKafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        log.info("Topic: " + topicName);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendUserMessage(User user) {
        ListenableFuture<SendResult<String, User>> future = userKafkaTemplate.send(topicName, user);

        log.info("Topic: " + topicName);

        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("Unable to send User Message", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, User> result) {
                log.info("Sent message=[" + user.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }


}
