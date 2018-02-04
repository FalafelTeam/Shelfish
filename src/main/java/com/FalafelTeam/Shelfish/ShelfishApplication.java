package com.FalafelTeam.Shelfish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
public class ShelfishApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelfishApplication.class, args);
	}
}
