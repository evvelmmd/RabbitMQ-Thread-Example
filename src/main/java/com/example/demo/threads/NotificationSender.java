package com.example.demo.threads;

import com.example.demo.model.Notification;
import com.example.demo.producer.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationSender implements Runnable{

    @Autowired
    NotificationProducer notificationProducer;



    @Override
    public void run() {

        try {
            // Displaying the thread that is running


            TimeUnit.SECONDS.sleep(2);
            Notification notification = notificationProducer.init();
            for (int i =0;i<30000;i++) {
                System.out.println(
                        "Thread " + Thread.currentThread().getId()
                                + " is running");
                notificationProducer.sendToQueue(notification);

            }


        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }


    }
}
