package com.em.cz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication 
@CrossOrigin("*")
public class EventMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventMangementApplication.class, args);
	}

}
