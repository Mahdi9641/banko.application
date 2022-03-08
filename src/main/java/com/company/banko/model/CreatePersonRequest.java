package com.company.banko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreatePersonRequest {


    private long id;

    private String firstName;

    private String lastName;

    private long age;

    private long nationalNumber;

    private Date birthDate;
}
