package org.reda.customerservice.controller;

import org.reda.customerservice.entity.Customer;
import org.reda.customerservice.repositories.CustomerRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    @GetMapping("/customers/{id}")
    public Customer getOneCustomer(@PathVariable int id) {
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping("/customers")
    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }
}
