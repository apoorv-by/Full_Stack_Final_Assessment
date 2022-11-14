package com.authentication.service.authentication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient // Enable eureka client to be discoverable
@EnableFeignClients // feign for intelligent routing of client /service
@SpringBootApplication
public class JWTServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(JWTServiceApplication.class, args);
	}

}
