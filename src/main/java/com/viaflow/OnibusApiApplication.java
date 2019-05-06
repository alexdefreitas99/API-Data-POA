package com.viaflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viaflow.integration.IntegrationService;

@SpringBootApplication
public class OnibusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnibusApiApplication.class, args);		
			IntegrationService.getRestTemplate();
	}
}
