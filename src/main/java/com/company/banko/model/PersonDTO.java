package com.company.banko.model;

import com.company.banko.CustomAnnotation.NationalNumberValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
//@ApiModel("PersonDTO")
public class PersonDTO implements Serializable {

    private Long id;


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String middlename;

    @NotNull
    private long age;

    @NotNull
    private Long officeId;

    @NotNull
    private String mobileNo;

    @NotNull
    private String emailAddress;


    @NotNull
    @NationalNumberValidation
    private String nationalNumber;

    @NotNull
    private Date birthDate;


}
