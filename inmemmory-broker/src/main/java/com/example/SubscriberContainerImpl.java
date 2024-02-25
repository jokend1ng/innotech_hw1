package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static com.example.SubscriberBeanPostProcessor.METHOD_2_BEAN;
@RequiredArgsConstructor
public class SubscriberContainerImpl implements SubscriberContainer {
    private final InmemoryBrocker inmemoryBrocker;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final ObjectMapper objectMapper;
    @PostConstruct
    @Override
    public void init() {
        METHOD_2_BEAN.forEach((key, value) -> executorService.submit(() -> {
            while (true) {
                final var message = inmemoryBrocker.take();
                key.invoke(
                        value,
                        objectMapper.readValue(message, key.getParameterTypes()[0])
                );
            }
        }));
    }
}
