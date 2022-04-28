package com.company.banko.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountTransferDTO {

    @NotNull
    private Date transactionDate;

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    private Long fromAccountId;

    @NotNull
    private Long toAccountId;

    @NotNull
    private String description;
}
