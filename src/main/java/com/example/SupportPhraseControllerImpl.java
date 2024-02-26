package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SupportPhraseControllerImpl implements SupportPhraseControllerGet, SupportPhraseControllerPost {
    private final SupportService service;
    private final SupportServiceKafkaOrOrigin serviceKafkaOrOrigin;
    @Override
    @GetMapping("/support")
    public SupportPhrase getSupportPhrase() {
        return service.getSupportPhrase();
    }

    @Override
    @PostMapping("/support")
    public void addPhrase(@RequestBody SupportPhrase request) {
        serviceKafkaOrOrigin.addSupportPhrase(request);
}

}
