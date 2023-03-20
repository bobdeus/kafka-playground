package com.example.kafkaplayground.controllers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class Testing {

    private final KafkaTemplate<String, String> template;

    public Testing(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @PostMapping(value = "/message")
    public String greeting(@RequestParam(name = "message_data", defaultValue = "") String message) {
        this.template.send("topic-1", "theKey-topic1", message);
        this.template.send("my-topic", "theKey-my-topic", message);
        return message;
    }


    @KafkaListener(id = "preAddedConsumer", topicPattern = ".*topic.*")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.printf("%s: %s%n", record.key(), record.value());
    }
}
