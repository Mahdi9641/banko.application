package com.company.banko.model;

import com.company.banko.domain.FinancialAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CardDTO {

    private Long id;

    @NotNull
    private Long accountNumber;

    @NotNull
    private String cardNumber;

    @NotNull
    private String iBan;

    @NotNull
    private int Cvv2;

    @NotNull
    private Date ExpirationDate;


}
