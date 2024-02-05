package com.example.innotech_hw1;

import com.example.innotech_hw1.dao.PhrasesDao;
import com.example.innotech_hw1.exception.MyException;
import com.example.innotech_hw1.model.Phrase;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/v1/support")
public class HelpServiceServlet extends HttpServlet {

    private final PhrasesDao words= new PhrasesDao();

    public PhrasesDao getWords() {
        return words;
    }

    @Override
    public void init(){
        words.getWords().add(new Phrase("У тебя все получится"));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        try (var writer = resp.getWriter()) {
            writer.write(words.getRandomPhrase());
        } catch (IOException e) {
            throw new MyException("Не вывести фразу");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (var reader = req.getReader()) {
            words.getWords().add(new Phrase(reader.readLine()));
            resp.setContentType("text/plain");
        } catch (IOException e) {
            throw new MyException("Не удалось добавить фразу");
        }
    }

}
