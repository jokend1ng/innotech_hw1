package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class SupportPhraseSubscriber {
    @Subscriber
    public void subscriber(SupportPhrase supportPhrase){
      log.info("Input message: " + supportPhrase.phrase());
    }
}
