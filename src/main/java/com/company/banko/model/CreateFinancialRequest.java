package com.company.banko.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor

public class CreateFinancialRequest {

    private long personId;

    @NotNull
    private long nationalNumber;

    private String description;

    private BigDecimal balance;

    @NotNull
    private long accountNumber;

    private Date creationDate;

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateFinancialRequest.class.getSimpleName() + "[", "]")
                .add("personId=" + personId)
                .add("nationalNumber=" + nationalNumber)
                .add("description='" + description + "'")
                .add("balance=" + balance)
                .add("accountNumber=" + accountNumber)
                .add("creationDate=" + creationDate)
                .toString();
    }
}
