package com.kafkaMsgEx1.consumer;

import com.kafkaMsgEx1.store.MessageStore;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    private MessageStore store;

    @KafkaListener(topics = "${app.topic.name}",groupId = "my-group")
    public void readMessage(String message){
        System.out.println("Received message from Kafka: " + message);
        store.addMessage(message);
    }
}
