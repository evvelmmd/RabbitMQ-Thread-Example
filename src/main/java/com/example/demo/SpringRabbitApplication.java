package com.example.demo;

import com.example.demo.model.Notification;
import com.example.demo.threads.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringRabbitApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringRabbitApplication.class, args);




	}

	@Autowired
	private ApplicationContext applicationContext;



		ExecutorService executorService = Executors.newFixedThreadPool(10);




	@EventListener(ApplicationStartedEvent.class)
	public void threadsUp(){
		Runnable sender = applicationContext.getBean(NotificationSender.class);

		IntStream.range(0,10).forEach(i-> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			executorService.submit(sender);
				}
		);

	}

}


