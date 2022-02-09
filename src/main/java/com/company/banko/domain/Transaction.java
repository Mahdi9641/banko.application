package com.company.banko.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.StringJoiner;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long deposit;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private String toAccount;

    public Transaction() {
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public Date getTransactionDate() {
        return transactionDate;
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

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deposit=" + deposit)
                .add("description='" + description + "'")
                .add("transactionDate=" + transactionDate)
                .add("toAccount='" + toAccount + "'")
                .toString();
    }
}
