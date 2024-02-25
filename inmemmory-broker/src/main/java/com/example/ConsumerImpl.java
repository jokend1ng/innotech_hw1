package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class ConsumerImpl implements Consumer {
   private final InmemoryBrocker broker;
   private final ObjectMapper objectMapper;

   @Override
   public Object getPhrase() {
      return  broker.take();
   }
}
