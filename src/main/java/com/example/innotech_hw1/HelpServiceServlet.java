package com.example.innotech_hw1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


@WebServlet("/v1/support")
public class HelpServiceServlet extends HttpServlet {
    public ArrayList<String> getWords() {
        return words;
    }

    private final ArrayList<String> words = new ArrayList<>();

    @Override
    public void init(){
        words.add("У тебя все получится");
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        int random = ThreadLocalRandom.current().nextInt(0, words.size());
        String message=words.get(random);
        resp.setContentType("text/plain");
        try (var writer = resp.getWriter()) {
            writer.write(message);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        try (var reader = req.getReader()) {
            words.add(reader.readLine());
        }
    }

}
