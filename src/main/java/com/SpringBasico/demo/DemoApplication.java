package com.SpringBasico.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ComponentScan(basePackages = "repositorys")
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		System.out.println("Iniciando la aplicación");
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Aplicación finalizada");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		List<Customer> allCustomers = this.repository.findAll();
		System.out.println("Número de clientes: " + allCustomers.size());

		Customer newCustomer = new Customer();
		newCustomer.setFirstName("John");
		newCustomer.setLastName("Doe");
		System.out.println("Guardando nuevo cliente...");
		this.repository.save(newCustomer);

		allCustomers = this.repository.findAll();
		System.out.println("Número de clientes: " + allCustomers.size());
	}
}
