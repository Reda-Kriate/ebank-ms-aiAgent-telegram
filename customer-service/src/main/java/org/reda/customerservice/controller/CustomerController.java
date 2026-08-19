package org.reda.customerservice.controller;

import org.reda.customerservice.entity.Customer;
import org.reda.customerservice.repositories.CustomerRepo;
import org.reda.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers/{id}")
    public Customer getOneCustomer(@PathVariable int id) {
        return customerService.getOneCustomer(id);
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers")
    public void saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
    }
}
