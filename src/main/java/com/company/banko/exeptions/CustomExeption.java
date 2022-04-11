package com.company.banko.exeptions;

public class CustomExeption extends RuntimeException {

    public CustomExeption(String errorMessage) throws Exception {

        super(errorMessage);
    }
}
