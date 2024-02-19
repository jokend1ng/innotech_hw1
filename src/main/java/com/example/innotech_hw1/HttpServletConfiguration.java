package com.example.innotech_hw1;

import org.example.MessageQueue;
import org.example.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class HttpServletConfiguration {
    @Bean
    public Publisher PublisherBean(MessageQueue queue) {
        return new Publisher(queue);
    }

    @Bean
    public MessageQueue MessageQueueBean() {
        return new MessageQueue();
    }
    @Bean
    public SubscriberHandlerBeanPostprocessor  SubscriberHandlerBeanPostprocessorBean(MessageQueue queue){
        return  new SubscriberHandlerBeanPostprocessor(queue);
    }

    @Bean
    public PhrasesDao PhrasesDaoBean(){
        return new PhrasesDaoImpl() ;

    }
}
