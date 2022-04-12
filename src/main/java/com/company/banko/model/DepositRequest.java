package com.company.banko.model;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.exeptions.CustomExeption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
public class DepositRequest {

    @NotNull
    private long fromAccount;

    @NotNull
    private String description;

    @NotNull
    private long toAccount;

    @NotNull
    private Date transactionDate;

    @NotNull
    private BigDecimal amount;

    @Override
    public String toString() {
        return new StringJoiner(", ", DepositRequest.class.getSimpleName() + "[", "]")
                .add("accountNumber=" + fromAccount)
                .add("description='" + description + "'")
                .add("toAccount='" + toAccount + "'")
                .add("transactionDate=" + transactionDate)
                .add("amount=" + amount)
                .toString();
    }
}
