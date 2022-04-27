package com.company.banko.model;

import com.company.banko.CustomAnnotation.NationalNumberValidation;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("PersonDTO")
public class PersonDTO implements Serializable {

    private Long id;


    private String firstName;


    private String lastName;


    private String middlename;


    private String mobileNo;


    private String emailAddress;


    @NotNull
    @NationalNumberValidation
    private String nationalNumber;


    private long age;


    private Date birthDate;


    private Long personId;


}
