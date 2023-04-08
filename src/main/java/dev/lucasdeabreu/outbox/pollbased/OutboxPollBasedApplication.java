package dev.lucasdeabreu.outbox.pollbased;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OutboxPollBasedApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutboxPollBasedApplication.class, args);
	}

}
