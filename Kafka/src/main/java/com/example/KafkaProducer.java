package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${kafka.topics.test-topic}")
    private String topic;

    private int messageNumber = 0;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessages(JsonMessage message) {
        while (true) {
            messageNumber++;
            kafkaTemplate.send(topic, String.valueOf(ThreadLocalRandom.current().nextLong()), message);
            log.info("Отправлено сообщение номер {}", messageNumber);
        }
    }
}
