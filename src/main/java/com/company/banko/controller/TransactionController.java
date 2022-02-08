package com.company.banko.controller;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Transaction;
import com.company.banko.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/banko")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(Transaction.class);

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/getTransaction")
    public Transaction getTransaction(){
        log.info("getTransaction");
        Transaction transaction = new Transaction();
        transactionRepository.findAll();
        return transaction;
    }

    @PostMapping("/createTransaction")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        log.info("createTransaction");
        transactionRepository.save(transaction);
        return transaction;
    }
    @PutMapping("/updateTransaction")
    public Transaction updateFinancialAccount(@RequestBody Transaction transaction){
        log.info("updatetransaction");
        transactionRepository.save(transaction);
        return transaction;
    }
    @DeleteMapping("/deleteTransaction")
    public Transaction deleteFinancialAccount(@RequestBody Transaction transaction){
        log.info("deletefinancialAccount");
        transactionRepository.deleteById(transaction.getId());
        transactionRepository.save(transaction);
        return transaction;
    }
}
