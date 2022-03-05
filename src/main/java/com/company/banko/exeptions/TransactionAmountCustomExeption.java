package com.company.banko.exeptions;

public class TransactionAmountCustomExeption extends RuntimeException{

    public TransactionAmountCustomExeption(String errorMessage) throws Exception {

        super(errorMessage);
    }
}
