package com.company.banko.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class FinancialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String accountNumber;

    private long balance;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    private Person person;

    @OneToMany
    private List<Transaction> transactions;


    public Date getCreationDate() {
        return creationDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public FinancialAccount() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
