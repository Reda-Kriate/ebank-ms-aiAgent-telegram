package org.reda.ebankservice.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    private int id;
    private String name;
    private String email;
}
