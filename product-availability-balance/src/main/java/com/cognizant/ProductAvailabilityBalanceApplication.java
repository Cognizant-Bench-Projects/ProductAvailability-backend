package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients("com.cognizant")
@SpringBootApplication
public class ProductAvailabilityBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAvailabilityBalanceApplication.class, args);
	}

}
