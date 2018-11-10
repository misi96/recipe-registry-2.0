package com.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SecApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SecApplication.class, args);
	}
	
	
	
}
