package com.eunkong.eschool.springbootkafkaproducer.resources;

import com.eunkong.eschool.springbootkafkaproducer.models.User;
import com.eunkong.eschool.springbootkafkaproducer.services.KafkaMessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaMessageSendService kafkaMessageSendService;

    @GetMapping("/publish/{message}")
    public String post(@PathVariable("message") final String message) {

        kafkaMessageSendService.sendMessage(message);
        return "Published successfully";
    }

    @GetMapping("/publish/users/{name}")
    public String postUser(@PathVariable("name") final String name) {
        kafkaMessageSendService.sendUserMessage(new User(name, "Computer", 12500L));

        return "Publish User successfully";
    }
}
