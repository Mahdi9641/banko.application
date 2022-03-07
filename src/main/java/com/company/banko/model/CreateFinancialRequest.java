package com.company.banko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class CreateFinancialRequest {

    private long personId;

    private String description;

    private long balance;

    private String accountNumber;

    private Date creationDate;

    private long nationalNumber;



}
