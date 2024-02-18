package com.example.innotech_hw1;

import org.example.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HttpServletController {
    private final Publisher publisher;
    private final PhrasesDao dao;
    private final SubscriberHandlerBeanPostprocessor postprocessor;

    public HttpServletController(Publisher publisher, PhrasesDao dao, SubscriberHandlerBeanPostprocessor postprocessor) {
        this.publisher = publisher;
        this.dao = dao;
        this.postprocessor = postprocessor;
        publisher.publishMessage("Все получится!");
    }

    @GetMapping
    public Phrase getPhrases() {
        return dao.getRandomPhrase();

    }

    @PostMapping
    public void addPhrases(Phrase message) {
        publisher.publishMessage(message.getMessage());
    }
}
