package com.company.banko.model;

import com.company.banko.domain.FinancialAccountStatusType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountDTO implements Serializable {

    private Long id;

    @NotNull
    private String personNationalNumber;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private Long accountNumber;

    @NotNull
    private Date creationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FinancialAccountStatusType status;


}
