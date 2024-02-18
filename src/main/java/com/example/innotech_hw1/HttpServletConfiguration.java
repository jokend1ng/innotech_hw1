package com.example.innotech_hw1;

import org.example.MessageQueue;
import org.example.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class HttpServletConfiguration {
    @Bean
    @Autowired
    public Publisher PublisherBean(MessageQueue queue) {
        return new Publisher(queue);
    }

    @Bean
    public MessageQueue MessageQueueBean() {
        return new MessageQueue();
    }
    @Bean
    public SubscriberHandlerBeanPostprocessor  SubscriberHandlerBeanPostprocessorBean(){
        return  new SubscriberHandlerBeanPostprocessor();
    }

    @Bean
    public PhrasesDao PhrasesDaoBean(){
        return new PhrasesDaoImpl() ;

    }
}
