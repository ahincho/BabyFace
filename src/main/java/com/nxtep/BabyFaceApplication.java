package com.nxtep;

import jakarta.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BabyFaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BabyFaceApplication.class, args);
	}
	@PostConstruct
	public void initialize() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	}
}
