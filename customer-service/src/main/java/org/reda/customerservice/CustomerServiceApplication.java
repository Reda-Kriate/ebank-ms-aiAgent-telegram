package org.reda.customerservice;

import org.reda.customerservice.entity.Customer;
import org.reda.customerservice.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepo customerRepo) {
		return args -> {
			List<Customer> customers = List.of(
					Customer.builder().name("Reda").email("Reda@gmail.com").build(),
					Customer.builder().name("Abdo").email("Abdo@gmail.com").build(),
					Customer.builder().name("Fofo").email("Fofo@gmail.com").build());

			customers.forEach(customer -> customerRepo.save(customer));
		};
	}

}
