package com.wow.WowPotato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WowPotatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WowPotatoApplication.class, args);
	}

}
