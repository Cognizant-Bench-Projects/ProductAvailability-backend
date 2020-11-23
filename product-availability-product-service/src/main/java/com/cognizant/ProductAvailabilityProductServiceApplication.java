package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductAvailabilityProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAvailabilityProductServiceApplication.class, args);
	}

}
