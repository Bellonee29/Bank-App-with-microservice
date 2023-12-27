package com.bellonee.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Accounts {


    private Long customerId;
    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
