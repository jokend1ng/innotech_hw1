package com.example.innotech_hw1.controllers;

import com.example.innotech_hw1.dao.PhrasesDao;
import com.example.innotech_hw1.model.Phrase;
import com.example.springframework.annotation.bean.factory.Autowred;

public class ControlServletImpl implements ControlServlet {
    @Autowred
    public PhrasesDao phrasesDao;

    @Override
    public String getPhrase() {
        return this.phrasesDao.getRandomPhrase();
    }

    @Override
    public void addPhrase(String phrase) {
        this.phrasesDao.getWords().add(new Phrase(phrase));
    }
}
