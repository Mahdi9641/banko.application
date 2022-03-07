package com.company.banko.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "transactionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "toAccount")
    private String toAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FinancialAccount_id")
    private FinancialAccount financialAccount;

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
