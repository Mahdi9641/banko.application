package com.company.banko.service;

import com.company.banko.domain.Transaction;
import com.company.banko.model.DepositRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {

    List<Transaction> findall();

    boolean insert(DepositRequest depositRequest) throws Exception;

    Transaction update(Transaction transaction);

    ResponseEntity<Void> delete(Long transactionId);
}
