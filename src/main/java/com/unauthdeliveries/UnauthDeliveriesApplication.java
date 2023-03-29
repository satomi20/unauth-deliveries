package com.unauthdeliveries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UnauthDeliveriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnauthDeliveriesApplication.class, args);
    }

}
