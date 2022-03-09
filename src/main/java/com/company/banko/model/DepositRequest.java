package com.company.banko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
public class DepositRequest {

    private long accountNumber;

    private String description;

    private String toAccount;

    private Date transactionDate;

    private BigDecimal amount;

    @Override
    public String toString() {
        return new StringJoiner(", ", DepositRequest.class.getSimpleName() + "[", "]")
                .add("accountNumber=" + accountNumber)
                .add("description='" + description + "'")
                .add("toAccount='" + toAccount + "'")
                .add("transactionDate=" + transactionDate)
                .add("amount=" + amount)
                .toString();
    }
}
