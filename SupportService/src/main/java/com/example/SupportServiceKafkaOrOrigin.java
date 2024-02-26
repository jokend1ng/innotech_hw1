package com.example;

import Kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupportServiceKafkaOrOrigin  implements SupportServiceAddPhrase{
   private final SupportService service;
    private final  KafkaProducer producer;
    @Override
    public void addSupportPhrase(SupportPhrase phrase) {
    if (producer.isKafkaIsEnable()) {
        producer.sendMessages(phrase);
        }
        service.addSupportPhrase(phrase);
    }
    }

