package com.microcommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroCommerceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroCommerceProductApplication.class, args);
	}

}