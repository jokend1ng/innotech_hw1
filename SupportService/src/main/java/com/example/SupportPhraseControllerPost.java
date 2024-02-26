package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SupportPhraseControllerPost {

    void addPhrase(@RequestBody SupportPhrase request);

}
