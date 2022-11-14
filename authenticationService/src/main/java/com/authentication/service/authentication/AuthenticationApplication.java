package com.authentication.service.authentication;

import com.authentication.service.authentication.model.User;
import com.authentication.service.authentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.stream.Stream;

@EnableEurekaClient
@SpringBootApplication
public class AuthenticationApplication{


	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}
}
