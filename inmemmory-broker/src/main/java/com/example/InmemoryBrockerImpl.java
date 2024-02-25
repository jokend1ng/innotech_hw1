package com.example;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InmemoryBrockerImpl implements InmemoryBrocker {

    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    @Override
    public void publish(String message) {
        messageQueue.offer(message);
    }
    @Override
    @SneakyThrows
    public String take(){
        return messageQueue.take();
    }
}
