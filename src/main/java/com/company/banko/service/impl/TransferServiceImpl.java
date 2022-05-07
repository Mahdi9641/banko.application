package com.company.banko.service.impl;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.AccountTransactionType;
import com.company.banko.domain.AccountTransferTransaction;
import com.company.banko.domain.Transaction;
import com.company.banko.model.AccountTransactionDTO;
import com.company.banko.model.AccountTransferDTO;
import com.company.banko.repository.AccountTransferTransactionRpository;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransferServiceImpl {

    private final FinancialAccountRepository financialAccountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionServiceImpl transactionService;
    private final AccountTransferTransactionRpository accountTransferTransactionRepository;

    @Autowired
    public TransferServiceImpl(FinancialAccountRepository financialAccountRepository, TransactionRepository transactionRepository, TransactionServiceImpl transactionService, AccountTransferTransactionRpository accountTransferTransactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;

        this.accountTransferTransactionRepository = accountTransferTransactionRepository;
    }

    @CustomLog
    public List<AccountTransferTransaction> findAll() {
        return accountTransferTransactionRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @CustomLog
    public void delete(Long id) {
        accountTransferTransactionRepository.deleteById(id);
    }


    @Transactional
    @CustomLog
    public AccountTransferTransaction handletransferFunds(AccountTransferDTO accountTransferDTO) {

        AccountTransactionDTO fromAccountTransactionDTO = new AccountTransactionDTO();
        fromAccountTransactionDTO.setTransactionType(AccountTransactionType.WITHDRAWAL);
        fromAccountTransactionDTO.setAmount(accountTransferDTO.getTransactionAmount());
        fromAccountTransactionDTO.setDateOf(accountTransferDTO.getTransactionDate());
        fromAccountTransactionDTO.setDescription(accountTransferDTO.getDescription());
        Transaction fromSavingsAccount = transactionService.handleWithdraw(accountTransferDTO.getFromAccountId(), fromAccountTransactionDTO);

        AccountTransactionDTO toAccountTransactionDTO = new AccountTransactionDTO();
        toAccountTransactionDTO.setTransactionType(AccountTransactionType.DEPOSIT);
        toAccountTransactionDTO.setAmount(accountTransferDTO.getTransactionAmount());
        toAccountTransactionDTO.setDateOf(accountTransferDTO.getTransactionDate());
        toAccountTransactionDTO.setDescription(accountTransferDTO.getDescription());
        Transaction toSavingsAccount = transactionService.handleDeposit(accountTransferDTO.getToAccountId(), toAccountTransactionDTO);
        ;

        AccountTransferTransaction accountTransferTransaction = new AccountTransferTransaction();
        accountTransferTransaction.setDate(accountTransferDTO.getTransactionDate());
        accountTransferTransaction.setAmount(accountTransferDTO.getTransactionAmount());
        accountTransferTransaction.setDescription(accountTransferDTO.getDescription());
        accountTransferTransaction.setFromSavingsTransaction(fromSavingsAccount);
        accountTransferTransaction.setToSavingsTransaction(toSavingsAccount);
        accountTransferTransactionRepository.save(accountTransferTransaction);

        return accountTransferTransaction;

    }
}
