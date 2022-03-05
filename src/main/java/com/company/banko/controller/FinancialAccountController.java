package com.company.banko.controller;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.model.CreateFinancialRequest;
import com.company.banko.service.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/banko")
public class FinancialAccountController {

    @Autowired
    private FinancialAccountService financialAccountService;

    @GetMapping(path = "/account/getFinancialAccount")
    private FinancialAccountService getFinancialAccount() {
        financialAccountService.findall();
        return financialAccountService;
    }

    @PostMapping(path = "/account/createFinancialAccount")
    private ResponseEntity<Object> createFinancialAccount(@RequestBody CreateFinancialRequest createFinancialRequest) throws Exception {
        financialAccountService.insert(createFinancialRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "active");
        map.put("result", "the Account is Create");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/account/deleteFinancialAccount/{financialAccountNumber}")
    private ResponseEntity<FinancialAccount> deleteFinancialAccount(@PathVariable Long financialAccountNumber) {
        financialAccountService.delete(financialAccountNumber);
        return ResponseEntity.noContent()
                .build();
    }

}


/* //private final FinancialAccountRepository financialAccountRepository;
    //private final PersonRepository personRepository;
    //private final TransactionRepository transactionRepository;

   *//* public FinancialAccountController(FinancialAccountRepository financialAccountRepository, PersonRepository personRepository, TransactionRepository transactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
    }*//*

    @GetMapping
    public FinancialAccount getFinancialAccount() {
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccountRepository.findAll();
        //  financialAccountRepository.findAll(Sort.by(Sort.Direction.ASC , "accountNumber"));
        //  financialAccountRepository.findAll(PageRequest.of(0 ,3 ));
        return financialAccount;
    }

    @PostMapping
    public FinancialAccount createFinancialAccount(@RequestBody CreateFinancialRequest createFinancialRequest) {
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setCreationDate(financialAccount.getCreationDate());
        financialAccount.setDescription(financialAccount.getDescription());
        financialAccount.setBalance(financialAccount.getBalance());
        financialAccount.setAccountNumber(financialAccount.getAccountNumber());
        Person person = personRepository.findPartyByNationalNumber(createFinancialRequest.getNationalNumber());
        financialAccount.setPerson(person);
        System.out.println("financialAccount.toString()" + financialAccount.toString());
        financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @PostMapping
    public Boolean depositAccount(@RequestBody DepositRequest accountTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(accountTransactionDTO.getAmount());
        FinancialAccount account = financialAccountRepository.findByAccountNumber(accountTransactionDTO.getAccountNumber());
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        Transaction tr = transactionRepository.save(transaction);
        System.out.println("tr.toString() = " + tr.toString()); //for see detail
        FinancialAccount financialAccount = financialAccountRepository.findByAccountNumber(account.getAccountNumber());
        return true;
    }

    @PutMapping
    public FinancialAccount updateFinancialAccount(@RequestBody FinancialAccount financialAccount) {
        financialAccount = financialAccountRepository.findByAccountNumber(financialAccount.getAccountNumber());
        financialAccount.setCreationDate(financialAccount.getCreationDate());
        financialAccount.setDescription(financialAccount.getDescription());
        financialAccount.setBalance(financialAccount.getBalance());
        financialAccount.setAccountNumber(financialAccount.getAccountNumber());
        financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @DeleteMapping
    public void deleteFinancialAccount(@PathVariable long accountNumber) {
        financialAccountRepository.deleteFinancialAccountByAccountNumber(accountNumber);
    }*/

/*  @PostMapping(path = "/account/depositAccount")
      private void depositAccount(@RequestBody DepositRequest depositRequest) {
          financialAccountService.Insert(depositRequest);
      }*/