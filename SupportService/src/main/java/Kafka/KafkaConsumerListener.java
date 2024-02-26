package Kafka;


import com.example.SupportPhrase;
import com.example.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@ConditionalOnProperty(value = "kafka.sync.enabled", havingValue = "true", matchIfMissing = true)
@Component
@Slf4j
public class KafkaConsumerListener {
   private final  SupportService service;
    @KafkaListener(
            id = "consumer-group-1",
            topics = "${kafka.topics.test-topic}",
            containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void handle(@Payload SupportPhrase message) {
        service.addSupportPhrase(message);
    }

    public void readMessage(SupportPhrase request) {
        String currentThreadName = Thread.currentThread().getName();
        log.info("Прочитано сообщение  в потоке: {}",  currentThreadName);
    }
}
