package com.company.banko.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Financial_Account_sequence")
@Table(name = "FinancialAccount", uniqueConstraints = {@UniqueConstraint(columnNames = {"accountNumber"}, name = "account_Number"),
        @UniqueConstraint(columnNames = {"creationDate"}, name = "creation_Date")})
public class FinancialAccount extends AbstractPersistableCustom implements Serializable {

    @Column(name = "accountNumber", nullable = false)
    private Long accountNumber;

    @Column(name = "balance", nullable = false)
    @Check(constraints = "available_count >= 0")
    private BigDecimal balance;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "creationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FinancialAccountStatusType status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialAccount", fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialAccount", fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setFinancialAccount(this);
    }

    public void validateForAccountBlock() {
        final FinancialAccountStatusType currentStatus = this.getStatus();
        if (FinancialAccountStatusType.BLOCK.equals(currentStatus)) {
            //throw new SavingsAccountBlockedException(this.getId());
        }
    }

    public void addCardTransaction(CardTransaction cardTransaction) {
        cards.add(cardTransaction.getCard());
        cardTransaction.getCard();
    }



}
