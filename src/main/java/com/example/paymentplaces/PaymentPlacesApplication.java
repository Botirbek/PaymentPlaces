package com.example.paymentplaces;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


@SpringBootApplication
@OpenAPIDefinition
@EnableSpringConfigured
public class PaymentPlacesApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(PaymentPlacesApplication.class, args);
    }

}
