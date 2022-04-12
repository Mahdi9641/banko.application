package com.company.banko.model;

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

public class CreateFinancialRequest {

    private long personId;

    @NotNull
    private String nationalNumber;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private long accountNumber;

    @NotNull
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
