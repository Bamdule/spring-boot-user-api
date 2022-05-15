package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExceptionApplication.class, args);
    }

}
