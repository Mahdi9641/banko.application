package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.Transaction;
import com.company.banko.exeptions.BadRequestAlertException;
import com.company.banko.model.AccountTransactionDTO;
import com.company.banko.service.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/banko/Transaction")
@SecurityRequirement(name = "basicAuth")
public class TransactionController {

    private static final String ENTITY_NAME = "Transaction";

    @Value("${banko.clientApp.name}")
    private String applicationName;


    private TransactionServiceImpl transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    public ResponseEntity <List<Transaction>> getAllSavingsTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/savings-account-transactions/{savingsId}/deposit")
       public ResponseEntity<Object> deposit(@PathVariable Long savingsId, @RequestBody AccountTransactionDTO accountTransactionDTO) throws URISyntaxException {
        if (accountTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("A new savingsAccountTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Transaction result = transactionService.handleDeposit(savingsId, accountTransactionDTO);
        //accountTransactionDTO.setId(result.id);
        accountTransactionDTO.setTransactionType(result.getTransactionType());
        accountTransactionDTO.setAmount(result.getAmount());
        Map<String, Object> map = new HashMap<>();
        map.put("TYPE", "DEPOSIT");
        map.put("RESULT", "The amount was deposit to the account" + result);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PostMapping("/savings-account-transactions/{savingsId}/withdraw")
    public ResponseEntity<Object> withdraw(@PathVariable Long savingsId, @RequestBody AccountTransactionDTO accountTransactionDTO) throws URISyntaxException {
        if (accountTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("A new savingsAccountTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Transaction result = transactionService.handleWithdraw(savingsId, accountTransactionDTO);
        accountTransactionDTO.setTransactionType(result.getTransactionType());
        accountTransactionDTO.setAmount(result.getAmount());
        accountTransactionDTO.setDescription(result.getDescription());
        Map<String, Object> map = new HashMap<>();
        map.put("TYPE", "Withdrawal");
        map.put("RESULT", "The amount was withdrawn from the account" + result);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }


    @DeleteMapping(path = "/account/deleteTransaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
