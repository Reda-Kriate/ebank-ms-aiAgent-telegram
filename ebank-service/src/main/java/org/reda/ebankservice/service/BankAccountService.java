package org.reda.ebankservice.service;

import org.reda.ebankservice.entities.BankAccount;
import org.reda.ebankservice.openFeign.CustomerFeignRestController;
import org.reda.ebankservice.repositories.BankAccountRepo;
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

    public List<BankAccount> getAllBankAccount() {
        List<BankAccount> bankAccounts = bankAccountRepo.findAll();
        bankAccounts.forEach(bankAccount -> {
            bankAccount.setCustomer(customerFeignRestController
                    .getOneCustomer(bankAccount.getCustomerId()));
        });
        return bankAccounts;
    }

    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = bankAccountRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Bank account not found"));
        bankAccount.setCustomer(customerFeignRestController
                .getOneCustomer(bankAccount.getCustomerId()));
        return bankAccount;
    }

    public BankAccount getBankAccountByCustomerId(int customerId) {
        return bankAccountRepo.findByCustomerId(customerId);
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepo.save(bankAccount);
    }

}
