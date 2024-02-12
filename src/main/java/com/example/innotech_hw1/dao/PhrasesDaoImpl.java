package com.example.innotech_hw1.dao;

import com.example.innotech_hw1.model.Phrase;
import com.example.springframework.annotation.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PhrasesDaoImpl implements PhrasesDao {

    private static final CopyOnWriteArrayList<Phrase> words = new CopyOnWriteArrayList<>();


    @Override
    public String getRandomPhrase() {
        int random = ThreadLocalRandom.current().nextInt(0, words.size());
        Phrase phrase= words.get(random);
        System.out.println(words.get(random).message());
        return phrase.message();
    }

    @Override
    public CopyOnWriteArrayList getWords() {
        return words;
    }


}
