package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.config.User;

@SpringBootApplication
@RestController
public class KafkaProducerApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topicName = "javatechie";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {	
		System.out.println("String message ::::::::::: " + name);
		template.send(topicName, "Hi  " + name  + " !!!  Welcome to Java techie");
		return "Data published";
	}

	
	@GetMapping("/publishJson")
	public String publishJsonMessage() {	
		User user = new User(2345, "Gomathi", new String[] {"Banglore", "Hno - 87", "Raj Street"});
		
		System.out.println("User message ::::::::::: " + user.toString());
		template.send(topicName, user);
		return "Data published";
	}

	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

}
