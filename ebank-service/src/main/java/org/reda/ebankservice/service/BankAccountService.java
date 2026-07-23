package org.reda.ebankservice.service;

import org.reda.ebankservice.entities.BankAccount;
import org.reda.ebankservice.repositories.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepo bankAccountRepo;

    public List<BankAccount> getAllBankAccount() {
        return bankAccountRepo.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        return bankAccountRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Bank account not found"));
    }

    public BankAccount getBankAccountByCustomerId(int customerId) {
        return bankAccountRepo.findByCustomerId(customerId);
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepo.save(bankAccount);
    }


}
