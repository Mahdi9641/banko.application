package com.company.banko.domain;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "Card_Transfer_Transaction_sequence")
@Entity
@Table(name = "CardTransferTransaction")
public class CardTransferTransaction extends AbstractPersistableCustom implements Serializable {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "from_card_transaction_id")
    private CardTransaction fromCardTransaction;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "to_card_transaction_id")
    private CardTransaction toCardTransaction;

    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date date;

    @Column(name = "amount", scale = 6, precision = 19, nullable = false)
    @Check(constraints = "available_count >= 0")
    private BigDecimal amount;

    @Column(name = "description", length = 100)
    private String description;
}
