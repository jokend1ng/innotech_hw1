package com.example.innotech_hw1;

import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PhrasesDaoImpl implements PhrasesDao {
    private final CopyOnWriteArrayList<Phrase> words = new CopyOnWriteArrayList<>();

    @Override
    public CopyOnWriteArrayList<Phrase> getWords() {
        return words;
    }

    @Override
    public Phrase getRandomPhrase() {
        int random = ThreadLocalRandom.current().nextInt(0, words.size());
        return words.get(random);
    }

    @Override
    public void addPhrase(Phrase phrase) {
        words.add(phrase);
    }
}
