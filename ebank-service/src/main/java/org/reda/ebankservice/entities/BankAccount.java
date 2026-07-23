package org.reda.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import org.reda.ebankservice.model.AccountType;
import org.reda.ebankservice.model.Customer;

import java.util.Date;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccount {
    @Id
    @GeneratedValue
    private String id;
    private double balance;
    private AccountType type;
    private Date createdAt;
    private int customerId;
    @Transient // This field will not be persisted in the database
    private Customer customer;
}
