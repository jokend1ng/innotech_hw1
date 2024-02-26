package Kafka;

import com.example.SupportPhrase;
import com.example.SupportServiceAddPhrase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@RequiredArgsConstructor
@Getter
@Component
public class KafkaProducer  {

    @Value("${kafka.topics.test-topic}")
    private String topic;

    @Value("${kafka.sync.enabled:true}")
    private boolean KafkaIsEnable;

    private int messageNumber = 0;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessages(SupportPhrase message) {
            messageNumber++;
            kafkaTemplate.send(topic, String.valueOf(ThreadLocalRandom.current().nextLong()), message);
            log.info("Отправлено сообщение номер {}", messageNumber);

    }


}
