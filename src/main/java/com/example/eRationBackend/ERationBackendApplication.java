package com.example.eRationBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.example.eRationBackend.Controller","com.example.eRationBackend.serviceImpl","com.example.eRationBackend.model","com.example.eRationBackend.*"})
@EnableJpaRepositories({"com.example.eRationBackend.dao.*"})
@SpringBootApplication
public class ERationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ERationBackendApplication.class, args);
	}

}
