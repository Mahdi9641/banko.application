package com.company.banko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.company.banko.*"})
public class BankoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankoApplication.class, args);
	}

}
