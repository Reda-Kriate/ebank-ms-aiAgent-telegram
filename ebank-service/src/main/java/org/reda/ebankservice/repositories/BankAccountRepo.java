package org.reda.ebankservice.repositories;

import org.reda.ebankservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
     BankAccount findByCustomerId(int customerId);
}
