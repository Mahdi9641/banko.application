package com.company.banko.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CardTransferDTO {

    @NotNull
    private Date transactionDate;

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    private String fromCardNumber;

    @NotNull
    private String toCardNumber;

    @NotNull
    private String description;

}
