package com.example;

import java.util.Set;

public interface SupportService {
    SupportPhrase getSupportPhrase();

    Set<SupportPhrase> getWords();

    SupportPhrase getrandomPhrase();
    void addSupportPhrase(SupportPhrase phrase);

}
