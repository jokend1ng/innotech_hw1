package com.example;

import org.springframework.web.bind.annotation.GetMapping;

public interface SupportPhraseControllerGet {
    @GetMapping("/support")
    SupportPhrase getSupportPhrase();
}
