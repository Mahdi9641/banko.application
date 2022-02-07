package com.company.banko.controller;

import com.company.banko.domain.*;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.PartyRepository;
import com.company.banko.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banko")
public class FinancialAccountController {

    private static final Logger log = LoggerFactory.getLogger(FinancialAccountController.class);

    private final FinancialAccountRepository financialAccountRepository;
    private final PartyRepository partyRepository;
    private final TransactionRepository transactionRepository;

    public FinancialAccountController(FinancialAccountRepository financialAccountRepository, PartyRepository partyRepository, TransactionRepository transactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.partyRepository = partyRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/getFinancialAccount")
    public FinancialAccount getFinancialAccount() {
        log.info("getFinancialAccount");
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccountRepository.findAll();
        financialAccountRepository.save(getFinancialAccount());
        return financialAccount;
    }

    @PostMapping("/createfinancialAccount")
    public FinancialAccount createFinancialAccount(@RequestBody AccountPartyDTO partyAccountDTO) {
        log.info("createFinancialAccount");
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setCreationDate(partyAccountDTO.getCreationDate());
        financialAccount.setExpirationDate(financialAccount.getExpirationDate());
        financialAccount.setDescription(partyAccountDTO.getDescription());
        financialAccount.setName(partyAccountDTO.getName());
        financialAccount.setBalance(partyAccountDTO.getBalance());
        financialAccount.setAccountNumber(partyAccountDTO.getAccountNumber());
        Party party = partyRepository.findPartyByNationalNumber(partyAccountDTO.getNationalNumber());
        financialAccount.setParty(party);
        financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @PostMapping("/depositAccount")
    public Boolean depositAccount(@RequestBody AccountTransactionDTO accountTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(accountTransactionDTO.getAmount());
        transaction.setDeposit(accountTransactionDTO.getDeposit());
        FinancialAccount account = financialAccountRepository.findByAccountNumber(accountTransactionDTO.getAccountNumber());
        account.setBalance(account.getBalance() + transaction.getAmount());
        Transaction tr = transactionRepository.save(transaction);
        System.out.println("tr.toString() = " + tr.toString()); //for see detail

        FinancialAccount financialAccount = financialAccountRepository.findByAccountNumber(account.getAccountNumber());
        return true;
    }

    @PutMapping("/updatefinancialAccount")
    public FinancialAccount updateFinancialAccount(@RequestBody FinancialAccount financialAccount) {
        log.info("updatefinancialAccount");
        financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @DeleteMapping("/deletefinancialAccount")
    public FinancialAccount deleteFinancialAccount(@RequestBody FinancialAccount financialAccount) {
        log.info("deletefinancialAccount");
        financialAccountRepository.deleteById(1L);
        financialAccountRepository.save(financialAccount);
        return financialAccount;
    }
}
