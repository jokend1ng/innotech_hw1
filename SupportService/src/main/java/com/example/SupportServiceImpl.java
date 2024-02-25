package com.example;

import org.springframework.stereotype.Component;

@Component
public class SupportServiceImpl implements  SupportService{

    @Override
    public SupportPhrase getSupportPhrase() {
        return new SupportPhrase("Держись!");
    }


}
