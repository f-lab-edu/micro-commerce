package com.microcommerce.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class MicroCommerceMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCommerceMemberApplication.class, args);
    }

}
