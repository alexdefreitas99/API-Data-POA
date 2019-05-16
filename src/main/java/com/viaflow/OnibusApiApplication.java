package com.viaflow;

import org.springframework.boot.SpringApplication;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class OnibusApiApplication {

	public static void main(String[] args) {
		ConnectionString connString = new ConnectionString(
				"mongodb+srv://viaflow:viaflow123@cluster0-vkfum.mongodb.net/integration?retryWrites=true");
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString).retryWrites(true)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("integration");

		SpringApplication.run(OnibusApiApplication.class, args);

	}
}
