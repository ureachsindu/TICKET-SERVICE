package com.dxc.tks.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

//@SpringBootApplication
@SpringBootApplication (scanBasePackages="com.dxc.tks.api")
@OpenAPIDefinition(info= @Info(title="Ticket Service ",version="1.0",description="Ticket Information"))

public class TicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

}
