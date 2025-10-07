package com.projetomvc.projetomvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetomvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetomvcApplication.class, args);
		System.out.println("Projeto MVC iniciado com sucesso!");
		System.out.println("Servidor rodando em http://localhost:8080");
	}

}
