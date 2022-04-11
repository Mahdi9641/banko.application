package com.company.banko.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor

@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Financial_Account_sequence")
@Table(name = "FinancialAccount", uniqueConstraints = {@UniqueConstraint(columnNames = {"accountNumber"}, name = "account_Number"),
        @UniqueConstraint(columnNames = {"creationDate"}, name = "creation_Name")})
public class FinancialAccount extends AbstractPersistableCustom implements Serializable {

    @Size(max = 2)
    @Column(name = "accountNumber", nullable = false)
    private Long accountNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "creationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialAccount", fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setFinancialAccount(this);
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
