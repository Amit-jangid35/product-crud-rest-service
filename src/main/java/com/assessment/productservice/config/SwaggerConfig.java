package com.assessment.productservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Product Service API")
				.description("RESTful API built using Spring Boot to perform CRUD operations on Product resource. "
						+ "This application includes layered architecture (Controller, Service, Repository), "
						+ "input validation using Bean Validation (JSR 380), global exception handling, and "
						+ "persistence using a relational database (e.g., MySQL/PostgreSQL). "
						+ "The API is documented using OpenAPI (Swagger) for easy testing and integration.")
				.version("v1.0").contact(new Contact().name("Amit Jangid").email("amitjangid490@gmail.com"))
				.license(new License().name("Apache 2.0")));
	}
}
