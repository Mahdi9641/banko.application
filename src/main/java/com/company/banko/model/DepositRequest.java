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
    private long accountNumber;

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
                .add("accountNumber=" + accountNumber)
                .add("description='" + description + "'")
                .add("toAccount='" + toAccount + "'")
                .add("transactionDate=" + transactionDate)
                .add("amount=" + amount)
                .toString();
    }

    public long to_account(long accountNumber) throws Exception {
        FinancialAccount account = new FinancialAccount();
        account.getAccountNumber();
        if (account.getAccountNumber() != to_account(toAccount)){
            throw new CustomExeption("the account does not exist");
        }
        return accountNumber;
    }
}
