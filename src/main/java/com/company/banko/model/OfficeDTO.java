package com.company.banko.model;

import com.company.banko.domain.Office;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OfficeDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date openingDate;

    @NotNull
    private String externalId;


    private Long parentid;



}
