package com.activemqEx.ptp;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiver {

    @JmsListener(destination = "testmq1")
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
