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
    private Long fromCardId;

    @NotNull
    private Long toCardId;

    @NotNull
    private String description;

}
