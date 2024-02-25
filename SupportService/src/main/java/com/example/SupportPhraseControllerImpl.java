package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SupportPhraseControllerImpl implements SupportPhraseControllerGet, SupportPhraseControllerPost {
    private final Consumer  consumer;
    private final KafkaProducer producer;
    private  final Publisher <SupportPhraseRequest> publisher;
//    private  final  KafkaConsumerListener consumerListener;
    @Override
    @GetMapping("/support")
    public SupportPhrase getSupportPhrase() {return (SupportPhrase) consumer.getPhrase();}

    @Override
    @Profile("inmemory")
    @PostMapping("/support")
    public void addPhrase(@RequestBody SupportPhraseRequest request) {
        publisher.publish(request);}

    @Override
    @Profile("Kafka")
    @PostMapping("/support")
    public void addKafkaPhrase(@RequestBody SupportPhraseRequest request) {
       publisher.publish(producer.sendMessages((JsonMessage) request));
    }
}
