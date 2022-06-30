package com.example.demo.listener;

import com.example.demo.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {


    @RabbitListener(queues = "avval-lsim-queue")
    public void handleMessage(Notification notification){

        System.out.println("Notification handled: " + notification.toString());
    }



}
