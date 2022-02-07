package com.company.banko.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



public class AccountPartyDTO {

    private long partyid;

    private String name;

    private String description;

    private long balance;

    private long accountNumber;

    private Date creationDate;

    private Date expirationDate;

    private long nationalNumber;


    public AccountPartyDTO() {
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(long nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getPartyid() {
        return partyid;
    }

    public void setPartyid(long partyid) {
        this.partyid = partyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

  }
