package org.reda.ebankservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.reda.ebankservice.model.AccountType;
import org.reda.ebankservice.model.Customer;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double balance;
    private AccountType type;
    private Date createdAt;
    private int customerId;
    @Transient // This field will not be persisted in the database
    private Customer customer;
}
