package com.example.innotech_hw1;

import com.example.innotech_hw1.dao.PhrasesDao;
import com.example.innotech_hw1.exception.MyException;
import com.example.innotech_hw1.model.Phrase;
import com.example.springframework.ApplicationContext;
import com.example.springframework.annotation.bean.factory.Autowred;
import com.example.springframework.annotation.stereotype.Controller;
import com.example.springframework.annotation.stereotype.GetMapping;
import com.example.springframework.annotation.stereotype.PostMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static java.lang.invoke.ConstantBootstraps.invoke;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {


    private final ApplicationContext applicationContext = new ApplicationContext();
    private PhrasesDao dao = (PhrasesDao) applicationContext.getBeansFactory().getBean("PhrasesDao");
    private Set<Class<?>> classes = new HashSet<Class<?>>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PhrasesDao getDao() {
        return dao;
    }


    @Override
    public void init() throws ServletException {
        classes = applicationContext.scanPackage("com.example", Controller.class);
        applicationContext.scanComponent("com.example");
        applicationContext.getAutowired();
        dao.getWords().add(new Phrase("у тебя все получится"));

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (Class<?> c : classes) {
            Optional<Method> first = Arrays.stream(c.getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(GetMapping.class) &&
                                    method.getAnnotation(GetMapping.class).value().equals(req.getRequestURI()))
                    .findFirst();
            if (first.isPresent()) {
                try {
                    Object invoke = first.get().invoke(c);
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                    resp.getWriter().write(objectMapper.writeValueAsString(invoke));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new MyException("Не вывести фразу");
                }
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        for (Object c : classes) {
            Optional<Method> first = Arrays.stream(c.getClass().getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(PostMapping.class) &&
                                    method.getAnnotation(PostMapping.class).value().equals(req.getRequestURI()))
                    .findFirst();
            if (first.isPresent()) {
                try (var reader = req.getReader()) {
                   String line = objectMapper.readValue(reader.readLine(),String.class);
                   resp.setContentType("text/plain");
                    Object invoke = first.get().invoke(line);
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
}
