package com.activemqEx.ptp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ActiveMQSenderRunner implements CommandLineRunner {
    @Autowired
    private JmsTemplate template;

    @Override
    public void run(String... args) throws Exception {
        template.send("testmq1",session -> session.createTextMessage("From Sender At"+new Date()));
        System.out.println("Message sent");
    }
}
