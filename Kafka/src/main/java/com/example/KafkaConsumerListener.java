package com.example;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;




@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerListener {
    @KafkaListener(
            id = "consumer-group-1",
            topics = "${kafka.topics.test-topic}",
            containerFactory = "kafkaListenerContainerFactory")
    public void handle(@Payload JsonMessage message) {
        readMessage(message);
    }

    public void readMessage(Object request) {
        String currentThreadName = Thread.currentThread().getName();
        log.info("Прочитано сообщение  в потоке: {}",  currentThreadName);
    }
}
