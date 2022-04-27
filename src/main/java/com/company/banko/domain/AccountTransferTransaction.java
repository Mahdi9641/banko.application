package com.company.banko.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "sequence-generator", initialValue = 1, sequenceName = "AccountTransferTransaction_sequence")
@Table(name = "m_account_transfer_transaction")
public class AccountTransferTransaction extends AbstractPersistableCustom implements Serializable {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "from_savings_transaction_id")
    private Transaction fromSavingsTransaction;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "to_savings_transaction_id")
    private Transaction toSavingsTransaction;

    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date date;

    @Column(name = "amount", scale = 6, precision = 19, nullable = false)
    private BigDecimal amount;

    @Column(name = "description", length = 100)
    private String description;
}
