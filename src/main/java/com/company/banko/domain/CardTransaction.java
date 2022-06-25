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
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Card_Transaction_sequence")
@Table(name = "CardTransaction")
public class CardTransaction extends AbstractPersistableCustom implements Serializable {

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
    @JoinColumn(name = "Card_id")
    private Card card;


    @Override
    public String toString() {
        return new StringJoiner(", ", CardTransaction.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount=" + amount)
                .add("description='" + description + "'")
                .add("transactionDate=" + transactionDate)
                .add("transactionType=" + transactionType)
                .add("card=" + card)
                .toString();
    }
}
