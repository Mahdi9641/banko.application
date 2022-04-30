package com.company.banko.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Transaction_sequence")
@Table(name = "Transaction")
public class Transaction extends AbstractPersistableCustom implements Serializable {

    @Column(name = "amount", nullable = false)
    @Check(constraints = "available_count >= 0")
    private BigDecimal amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "transactionDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private AccountTransactionType transactionType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FinancialAccount_id")
    private FinancialAccount financialAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;


    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deposit=" + amount)
                .add("description='" + description + "'")
                .add("transactionDate=" + transactionDate)
                .toString();
    }
}
