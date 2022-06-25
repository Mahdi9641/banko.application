package com.company.banko.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Card_sequence")
@Table(name = "Card", uniqueConstraints = {@UniqueConstraint(columnNames = {"cardNumber"}, name = "card_Number")})
public class Card extends AbstractPersistableCustom implements Serializable {

    @Column(name = "cardNumber", nullable = false)
    private String cardNumber;

    @Column(name = "iBan", nullable = false)
    private String iBan;

    @Column(name = "Cvv2", nullable = false)
    private int Cvv2;

    @Column(name = "ExpirationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ExpirationDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FinancialAccount_id")
    private FinancialAccount financialAccount;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    private Set<CardTransaction> cardTransactionSet;



}
