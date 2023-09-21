package com.andlopper.productapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApiApplication {

	private static final Logger log = LoggerFactory.getLogger(ProductApiApplication.class);

	public static void main(String[] args) {
		log.info("[main] Iniciando API de gerenciamento de produtos");
		SpringApplication.run(ProductApiApplication.class, args);
		log.info("[main] API iniciada e pronta para receber requisições");
	}

}
