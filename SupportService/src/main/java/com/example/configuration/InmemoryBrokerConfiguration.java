package com.example.configuration;

import Kafka.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class InmemoryBrokerConfiguration {
    private final ProducerFactory<Object, Object> producerFactory;
    @Bean
    public  KafkaTemplate<Object,Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory);
    }
    @Bean
    public ObjectMapper ObjectMapper() {
    return new ObjectMapper();
    }
    @Bean
    public KafkaProducer kafkaProducer(KafkaTemplate<Object,Object> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate);
    }


}
