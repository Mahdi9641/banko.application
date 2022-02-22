package com.company.banko.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal amount;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private String toAccount;

    @ManyToOne
    @JoinColumn(name = "FinancialAccount_id")
    private FinancialAccount financialAccount;

    public Transaction() {
    }

    public Transaction(long id, BigDecimal amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTransactionDate(Date date) {
        return transactionDate;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deposit=" + amount)
                .add("description='" + description + "'")
                .add("transactionDate=" + transactionDate)
                .add("toAccount='" + toAccount + "'")
                .toString();
    }
}
