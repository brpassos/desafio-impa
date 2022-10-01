package com.brunopassos.desafioimpa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Desafio IMPA - Tarefas API",
		version = "1.0",
		description = "API para gerencimaento de Tarefas"
))
public class DesafioImpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioImpaApplication.class, args);
	}

}
