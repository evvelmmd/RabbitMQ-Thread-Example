package com.example.demo.producer;

import com.example.demo.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class NotificationProducer {

    public Executor exec(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setThreadNamePrefix("Thead push executor- ");
        executor.setThreadPriority(Thread.NORM_PRIORITY);
        executor.initialize();


return executor;

    }


    @PostConstruct
    public Notification init(){
        Notification notification =  new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("Mesaj");
        notification.setSeen(Boolean.FALSE);

        return notification;


    }
    @Value("${sr.rabbit.routing.name}")
    private String routingName;


    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int counter=0;
    @RabbitHandler
    public void sendToQueue(Notification notification){
        System.out.println("Notification send ID "+notification.getNotificationId());

        rabbitTemplate.convertAndSend(exchangeName,routingName,notification);
    }
}
