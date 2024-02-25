package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class PublisherImpl implements Publisher {
    private final ObjectMapper objectMapper;
    private final InmemoryBrocker inmemoryBrocker;


    @SneakyThrows
    @Override
    public void publish(Object o) {
        inmemoryBrocker.publish(objectMapper.writeValueAsString(o));
    }
}
