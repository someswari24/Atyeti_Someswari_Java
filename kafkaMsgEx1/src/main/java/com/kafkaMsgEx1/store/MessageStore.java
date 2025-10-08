package com.kafkaMsgEx1.store;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageStore {
    private List<String >listMessage=new ArrayList<>();

    public void addMessage(String message){
        listMessage.add(message);
    }

    public String getAllMessages(){
        return listMessage.toString();
    }
}
