package org.reda.ebankservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.reda.ebankservice.entities.BankAccount;
import org.reda.ebankservice.model.Customer;
import org.reda.ebankservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccount> getAllBankAccount() {
        return bankAccountService.getAllBankAccount();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }

    @GetMapping("/bankAccounts/customer/{customerId}")
    public BankAccount getBankAccountByCustomerId(@PathVariable int customerId) {
        return bankAccountService.getBankAccountByCustomerId(customerId);
    }

    @PostMapping("/bankAccounts")
    public BankAccount saveBankAccount(BankAccount bankAccount){
        bankAccount.setCreatedAt(new Date());
        return bankAccountService.saveBankAccount(bankAccount);
    }

}

