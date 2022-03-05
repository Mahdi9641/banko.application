package com.company.banko.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class FinancialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private long accountNumber;

    private BigDecimal balance;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;

    @OneToMany(mappedBy = "financialAccount", fetch = FetchType.LAZY)
    private List<Transaction> transactions;


    public FinancialAccount(long id, long accountNumber) {
        this.id = id;
        this.accountNumber = accountNumber;
    }

    public FinancialAccount() {
    }

    public FinancialAccount(long id, long accountNumber, String description) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Date getCreationDate(Date creationDate) {
        return creationDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", FinancialAccount.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("accountNumber=" + accountNumber)
                .add("balance=" + balance)
                .add("description='" + description + "'")
                .add("creationDate=" + creationDate)
                .add("person=" + person)
                .add("transactions=" + transactions)
                .toString();
    }
}
