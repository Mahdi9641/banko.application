package com.company.banko.exeptions;

public class PersonAgeCustomExeption extends RuntimeException {

    public PersonAgeCustomExeption(String errorMessage) throws Exception {

        super(errorMessage);
    }
}
