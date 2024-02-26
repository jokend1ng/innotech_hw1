package com.example;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SupportServiceImpl implements SupportService, SupportServiceAddPhrase {
    private final Set<SupportPhrase> words =ConcurrentHashMap.newKeySet();
    @Override
    public SupportPhrase getSupportPhrase() {
        return new SupportPhrase("Держись!");
    }


    @Override
    public Set <SupportPhrase> getWords() {
        return words;
    }
    @Override
    public SupportPhrase getrandomPhrase(){
        return words.stream().toList().get(ThreadLocalRandom.current().nextInt(0, words.size()));
    }
    @Override
    public void addSupportPhrase(SupportPhrase phrase){
        words.add(phrase);
    }
}
