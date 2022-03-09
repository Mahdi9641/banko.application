package com.company.banko.controller;

import com.company.banko.domain.Transaction;
import com.company.banko.model.DepositRequest;
import com.company.banko.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/banko/Transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;



    @GetMapping
    private ResponseEntity<List<Transaction>> getTransaction() {
        List<Transaction> transactions = transactionService.findall();
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Object> createTransaction(@RequestBody DepositRequest depositRequest) throws Exception {
        transactionService.insert(depositRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "Ok");
        map.put("result", "The amount was deposited");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }


    @DeleteMapping(path = "/transaction/deleteTransaction/{transactionId}")
    public ResponseEntity<Transaction> deleteTransaction(@RequestBody long transactionId) {
        transactionService.delete(transactionId);
        return ResponseEntity.noContent()
                .build();
    }
}
