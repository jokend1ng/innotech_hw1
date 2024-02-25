package com.example.configuration;

import com.example.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class InmemoryBrokerConfiguration {
    private final ProducerFactory<Object, Object> producerFactory;
    @Bean
    public ObjectMapper ObjectMapper() {
    return new ObjectMapper();
    }
    @Bean
    public InmemoryBrocker inmemoryBrocker(){
        return new InmemoryBrockerImpl();
    }

    @Bean
    public SubscriberContainer subscriberContainer(ObjectMapper objectMapper,InmemoryBrocker inmemoryBrocker){
        return new SubscriberContainerImpl(inmemoryBrocker,objectMapper);
    }
    @Bean
    public BeanPostProcessor subscriberBeanPostProcessor(){
        return new SubscriberBeanPostProcessor();
    }
    @Bean
    public Publisher<SupportPhraseRequest> supportPhraseRequestPublisher(InmemoryBrocker inmemoryBrocker, ObjectMapper objectMapper){
        return new PublisherImpl(objectMapper,inmemoryBrocker);
    }
    @Bean
    public Consumer<SupportPhraseRequest> supportPhraseConsumer(InmemoryBrocker inmemoryBrocker, ObjectMapper objectMapper){
        return new ConsumerImpl(inmemoryBrocker,objectMapper);
    }
    @Bean
    @Profile("Kafka")
    public KafkaConsumerListener consumerListener(){
        return new KafkaConsumerListener();
    }

//    @Bean
//    @Profile("Kafka")
//    public KafkaProducer producer(){
//       return new KafkaProducer();
//    }
}
