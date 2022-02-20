package com.company.banko.controller;

import com.company.banko.domain.*;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.PersonRepository;
import com.company.banko.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banko")
public class FinancialAccountController {

    private static final Logger log = LoggerFactory.getLogger(FinancialAccountController.class);

    private final FinancialAccountRepository financialAccountRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;

    public FinancialAccountController(FinancialAccountRepository financialAccountRepository, PersonRepository personRepository, TransactionRepository transactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/getFinancialAccount")
    public FinancialAccount getFinancialAccount() {
        log.info("getFinancialAccount");
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccountRepository.findAll();
      //  financialAccountRepository.findAll(Sort.by(Sort.Direction.ASC , "accountNumber"));
      //  financialAccountRepository.findAll(PageRequest.of(0 ,3 ));
        return financialAccount;
    }

    @PostMapping("/createfinancialAccount")
    public FinancialAccount createFinancialAccount(@RequestBody AccountPersonDTO accountPersonDTO) {
        log.info("createFinancialAccount");
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setCreationDate(financialAccount.getCreationDate());
        financialAccount.setDescription(financialAccount.getDescription());
        financialAccount.setBalance(financialAccount.getBalance());
        financialAccount.setAccountNumber(financialAccount.getAccountNumber());
        Person  person = personRepository.findPartyByNationalNumber(accountPersonDTO.getNationalNumber());
        financialAccount.setPerson(person);
        System.out.println("financialAccount.toString()" + financialAccount.toString());
        financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @PostMapping("/depositAccount")
    public Boolean depositAccount(@RequestBody AccountTransactionDTO accountTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setDeposit(accountTransactionDTO.getDeposit());
        FinancialAccount account = financialAccountRepository.findByAccountNumber(accountTransactionDTO.getAccountNumber());
        account.setBalance(account.getBalance() + transaction.getDeposit());
        Transaction tr = transactionRepository.save(transaction);
        System.out.println("tr.toString() = " + tr.toString()); //for see detail
        FinancialAccount financialAccount = financialAccountRepository.findByAccountNumber(account.getAccountNumber());
        return true;
    }
    @PutMapping("/updatefinancialAccount")
    public FinancialAccount updateFinancialAccount(@RequestBody FinancialAccount financialAccount) {
       log.info("updatefinancialAccount");
       financialAccount = financialAccountRepository.findByAccountNumber(financialAccount.getAccountNumber());
        financialAccount.setCreationDate(financialAccount.getCreationDate());
        financialAccount.setDescription(financialAccount.getDescription());
        financialAccount.setBalance(financialAccount.getBalance());
        financialAccount.setAccountNumber(financialAccount.getAccountNumber());
       financialAccountRepository.save(financialAccount);
    return financialAccount;
   }

    @DeleteMapping("/deletefinancialAccount/{accountNumber}")
    public void deleteFinancialAccount(@PathVariable String accountNumber) {
        log.info("deletefinancialAccount");
       financialAccountRepository.deleteFinancialAccountByAccountNumber(accountNumber);
    }
}



