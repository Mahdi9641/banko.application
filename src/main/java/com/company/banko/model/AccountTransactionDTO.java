package com.company.banko.model;

import com.company.banko.domain.AccountTransactionType;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.exeptions.CustomExeption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
public class AccountTransactionDTO implements Serializable {

    private Long id;

    @NotNull
    private AccountTransactionType transactionType;

    @NotNull
    private Date dateOf;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal amount;


}
