package com.example.innotech_hw1.controllers;

import com.example.innotech_hw1.model.Phrase;
import com.example.springframework.annotation.stereotype.Controller;
import com.example.springframework.annotation.stereotype.GetMapping;
import com.example.springframework.annotation.stereotype.PostMapping;

@Controller
public interface ControlServlet {
    @GetMapping("/help-service/v1/support")
    String getPhrase();
    @PostMapping("/help-service/v1/support")
    void  addPhrase(String phrase);
}
