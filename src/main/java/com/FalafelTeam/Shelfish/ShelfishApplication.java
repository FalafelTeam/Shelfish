package com.FalafelTeam.Shelfish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.FalafelTeam.Shelfish.controller","com.FalafelTeam.Shelfish.model", "com.FalafelTeam.Shelfish.repository"})
public class ShelfishApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelfishApplication.class, args);
	}
}
