package com.example.innotech_hw1;
import java.util.concurrent.CopyOnWriteArrayList;

public interface PhrasesDao {
    Phrase getRandomPhrase();
    CopyOnWriteArrayList<Phrase> getWords();

    @Subscriber
    void addPhrase(Phrase phrase);
}
