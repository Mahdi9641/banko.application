package com.company.banko.controller;

import com.company.banko.domain.Transaction;
import com.company.banko.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/banko/Transaction-Request")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transactionRepository.findAll();
        return transaction;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @PutMapping
    public Transaction updateFinancialAccount(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @DeleteMapping
    public Transaction deleteFinancialAccount(@RequestBody Transaction transaction) {
        transactionRepository.deleteById(transaction.getId());
        transactionRepository.save(transaction);
        return transaction;
    }
}
