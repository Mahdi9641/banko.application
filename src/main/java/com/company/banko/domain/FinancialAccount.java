package com.company.banko.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class FinancialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name = "accountNumber")
    private long accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;

    @OneToMany(mappedBy = "financialAccount", fetch = FetchType.LAZY)
    private List<Transaction> transactions;


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
