package com.company.banko.model;

import com.company.banko.domain.AccountTransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class CardTransactionDTO implements Serializable {

        private Long id;

        @NotNull
        private AccountTransactionType transactionType;

        @NotNull
        private Date dateOf;

        @NotNull
        private String description;

        @NotNull
        private BigDecimal amount;

        @NotNull
        private Long accountNumber;
}
