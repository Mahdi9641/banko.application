package com.company.banko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DepositRequest {

    private long accountNumber;

    private String description;

    private Date transactionDate;

    private BigDecimal amount;


}
