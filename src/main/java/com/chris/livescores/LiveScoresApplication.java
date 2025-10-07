package com.chris.livescores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // enable background jobs
public class LiveScoresApplication {
	public static void main(String[] args) {
		SpringApplication.run(LiveScoresApplication.class, args);
	}
}