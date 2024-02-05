package com.example.innotech_hw1.dao;

import com.example.innotech_hw1.model.Phrase;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PhrasesDao {
    private final CopyOnWriteArrayList<Phrase> words = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Phrase> getWords() {
        return words;
    }
    public String getRandomPhrase() {
        int random = ThreadLocalRandom.current().nextInt(0, words.size());
        Phrase message=words.get(random);
        return message.getPhrase();
    }
}
