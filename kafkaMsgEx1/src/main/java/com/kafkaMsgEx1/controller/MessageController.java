package com.kafkaMsgEx1.controller;

import com.kafkaMsgEx1.producer.MessageProducer;
import com.kafkaMsgEx1.store.MessageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageProducer producer;
    @Autowired
    private MessageStore store;

    @GetMapping("/send")
    public String sendMessage(@RequestParam("message")String message){
        String status= producer.sendMessage(message);
        return status;
    }

    @GetMapping("/readAll")
    public String getAllMessages(){
        return store.getAllMessages();
    }
}
