package org.dsi;

import org.dsi.entity.Supplier;
import org.dsi.repo.SupplierRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceSupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSupplierApplication.class, args);
	}
	
	@Bean
    CommandLineRunner start(SupplierRepo supplierRepo){
        return args -> {
        	supplierRepo.save(new Supplier(null,"Talel","talel@gmail.com"));
        	supplierRepo.save(new Supplier(null,"seif","seif@gmail.com"));
        	supplierRepo.save(new Supplier(null,"nour","nour@gmail.com"));
        };
    }

}
