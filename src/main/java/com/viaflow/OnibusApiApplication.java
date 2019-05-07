package com.viaflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class OnibusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnibusApiApplication.class, args);		
	}
}
