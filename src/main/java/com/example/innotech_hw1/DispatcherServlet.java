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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class DispatcherServlet extends HttpServlet  {


    private final ApplicationContext applicationContext = new ApplicationContext();
    private PhrasesDao dao= (PhrasesDao) applicationContext.getBeansFactory().getBean("PhrasesDao");
    private final ObjectMapper mapper = new ObjectMapper();
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        for (Class<?> c : classes) {
            Optional<Method> first = Arrays.stream(c.getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(GetMapping.class) &&
                                    method.getAnnotation(GetMapping.class).value().equals(req.getRequestURI()))
                    .findFirst();
            if (first.isPresent()) {
                try (var writer = resp.getWriter()) {
                    writer.write(objectMapper.writeValueAsString(dao.getRandomPhrase()));
                    resp.setContentType("application/json");
                } catch (IOException e) {
                    throw new MyException("Не вывести фразу");
                }
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        for (Class<?> c : classes) {
            Optional<Method> first = Arrays.stream(c.getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(PostMapping.class) &&
                                    method.getAnnotation(PostMapping.class).value().equals(req.getRequestURI()))
                    .findFirst();
            if (first.isPresent()) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    req.getReader().lines().forEach(stringBuilder::append);
                    Optional<Class<?>> parameter = Arrays.stream(first.get().getParameterTypes()).findFirst();
                    Object result;
                    if (parameter.isPresent()) {
                        result = first.get().invoke(applicationContext.getBeansFactory().getBean(c.getSimpleName()),
                                objectMapper.readValue(stringBuilder.toString(), parameter.get()));
                    } else {
                        result = first.get().invoke(applicationContext.getBeansFactory().getBean(c.getSimpleName()));
                    }
                    resp.setContentType("application/json");
                    if (result != null) {
                           resp.getWriter().print(objectMapper.writeValueAsString(result));
                    }
                } catch (IllegalAccessException | IOException e) {
                    resp.setStatus(204);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            } else {
                resp.setStatus(404);
            }
        }
    }

}
