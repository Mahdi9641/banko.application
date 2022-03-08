package com.company.banko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class CreateFinancialRequest {

    private long personId;

    private long nationalNumber;

    private String description;

    private BigDecimal balance;

    private long accountNumber;

    private Date creationDate;





}
