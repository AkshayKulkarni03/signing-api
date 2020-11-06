package com.signing.demo.signingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SigningApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SigningApiApplication.class, args);
    }

}
