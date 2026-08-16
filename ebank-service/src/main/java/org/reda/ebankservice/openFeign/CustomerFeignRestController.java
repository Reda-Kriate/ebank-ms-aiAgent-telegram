package org.reda.ebankservice.openFeign;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.reda.ebankservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignRestController {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "CustomerFallback")
    Customer getOneCustomer(@PathVariable int id);

default Customer CustomerFallback(int id, Exception e) {
        return new Customer(id, "NoName", "NoEmail");
    }
}
