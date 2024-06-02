package com.example.ramengo;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "ramenGO!",
				version = "1.0.0",
				description = "This API allows users to list available broths, available proteins and place an order."
		)
)
public class RamengoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamengoApplication.class, args);
	}

}
