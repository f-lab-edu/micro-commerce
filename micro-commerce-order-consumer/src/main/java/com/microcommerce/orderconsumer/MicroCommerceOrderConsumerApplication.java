package com.microcommerce.orderconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@EnableJpaAuditing
@EnableFeignClients
@EnableDiscoveryClient
@EnableKafka
@SpringBootApplication
public class MicroCommerceOrderConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroCommerceOrderConsumerApplication.class, args);
	}

}
