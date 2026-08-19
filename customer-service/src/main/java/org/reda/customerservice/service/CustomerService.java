package org.reda.customerservice.service;

import org.reda.customerservice.entity.Customer;
import org.reda.customerservice.repositories.CustomerRepo;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @McpTool(description = "get customer by id")
    public Customer getOneCustomer(@McpToolParam(description = "customer id") int id) {
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @McpTool(description = "get all customers")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @McpTool(description = "save new customer")
    public void saveCustomer(@McpToolParam(description = "customer information") Customer customer) {
        customerRepo.save(customer);
    }
}
