package org.reda.ebankservice.service;

import org.reda.ebankservice.entities.BankAccount;
import org.reda.ebankservice.openFeign.CustomerFeignRestController;
import org.reda.ebankservice.repositories.BankAccountRepo;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepo bankAccountRepo;
    private final CustomerFeignRestController customerFeignRestController;


    public BankAccountService(BankAccountRepo bankAccountRepo, CustomerFeignRestController customerFeignRestController) {
        this.bankAccountRepo = bankAccountRepo;
        this.customerFeignRestController = customerFeignRestController;
    }

    @McpTool(description = "get all bank accounts")
    public List<BankAccount> getAllBankAccount() {
        List<BankAccount> bankAccounts = bankAccountRepo.findAll();
        bankAccounts.forEach(bankAccount -> {
            bankAccount.setCustomer(customerFeignRestController
                    .getOneCustomer(bankAccount.getCustomerId()));
        });
        return bankAccounts;
    }

    @McpTool(description = "get bank account by id")
    public BankAccount getBankAccountById(@McpToolParam(description = "bank account id") String id) {
        BankAccount bankAccount = bankAccountRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Bank account not found"));
        bankAccount.setCustomer(customerFeignRestController
                .getOneCustomer(bankAccount.getCustomerId()));
        return bankAccount;
    }

    @McpTool(description = "get customer bank accounts by customer id")
    public BankAccount getBankAccountByCustomerId(@McpToolParam(description = "customer id") int customerId) {
        return bankAccountRepo.findByCustomerId(customerId);
    }

    @McpTool(description = "save new bank account")
    public BankAccount saveBankAccount(@McpToolParam(description = "bank account information") BankAccount bankAccount) {
        bankAccount.setId(null);
        return bankAccountRepo.save(bankAccount);
    }

}
