package com.example.kafkaplayground.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean(name = "bob")
    public NewTopic topic() {
        return TopicBuilder.name("topic-1")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
