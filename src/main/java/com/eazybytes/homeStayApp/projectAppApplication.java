package com.eazybytes.homeStayApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eazybytes.homeStayApp.repository")
@EntityScan("com.eazybytes.homeStayApp.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class projectAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(projectAppApplication.class, args);
	}

}
