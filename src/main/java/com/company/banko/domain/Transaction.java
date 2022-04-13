package com.company.banko.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private BigDecimal amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "transactionDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "toAccount", nullable = false)
    private long toAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
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
