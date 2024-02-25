package com.example;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

@Configuration
@Profile("Kafka")
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfiguration {

    private static final String DLT_TOPIC_SUFFIX = ".dlt";

    private final ProducerFactory<Object, Object> producerFactory;
    private final ConsumerFactory<Object, Object> consumerFactory;

    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            DefaultErrorHandler errorHandler
    ) {

        ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        kafkaListenerContainerFactory.setCommonErrorHandler(errorHandler);
        kafkaListenerContainerFactory.setConcurrency(1);
        return kafkaListenerContainerFactory;
    }

    @Bean
    public DeadLetterPublishingRecoverer publisher(KafkaTemplate<Object, Object> bytesTemplate) {
        return new DeadLetterPublishingRecoverer(bytesTemplate, (consumerRecord, exception) ->
                new TopicPartition(consumerRecord.topic() + DLT_TOPIC_SUFFIX, consumerRecord.partition()));
    }

    @Bean
    public DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer deadLetterPublishingRecoverer) {
        final var handler = new DefaultErrorHandler(deadLetterPublishingRecoverer);
        handler.addNotRetryableExceptions(Exception.class);
        return handler;
    }
}
