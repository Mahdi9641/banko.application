package com.company.banko.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountTransferDTO {

    private Date transactionDate;
    private BigDecimal transactionAmount;
    private Long fromAccountId;
    private Long toAccountId;
    private String description;
}
