package org.reda.ebankservice;

import org.reda.ebankservice.entities.BankAccount;
import org.reda.ebankservice.model.AccountType;
import org.reda.ebankservice.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner (BankAccountService bankAccountService){
        return args -> {
            for(int i=0;i<3;i++){
                for (int j=0;j<5;j++){

                    bankAccountService.saveBankAccount(BankAccount.builder()
                            .balance(Math.random()*10000+5000)
                            .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                            .createdAt(new Date())
                            .customerId(i+1)
                            .build());
                }

            }
        };
    }

}

