package com.company.banko.service;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Transaction;
import com.company.banko.model.DepositRequest;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final FinancialAccountRepository financialAccountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, FinancialAccountRepository financialAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.financialAccountRepository = financialAccountRepository;
    }


    @Override
    public List<Transaction> findall() {
        transactionRepository.findAll();
        return transactionRepository.findAll();
    }



    @Override
    public boolean insert(DepositRequest depositRequest) throws Exception {
        DepositRequest depositRequest1 = new DepositRequest();
        Transaction transaction = new Transaction();
        transaction.setAmount(transaction.getAmount());
        transaction.setToAccount(transaction.getToAccount());
        transaction.setDescription(transaction.getDescription());
        transaction.setTransactionDate(transaction.getTransactionDate());
        FinancialAccount account = financialAccountRepository.findByAccountNumber(depositRequest.getAccountNumber());
        account.setTransactions((List<Transaction>) account.getBalance().add(transaction.getAmount()));
        FinancialAccount acc = financialAccountRepository.save(account);
        transactionRepository.save(transaction);
        System.out.println("tr.toString() = " + transaction.toString());
        transaction = transactionRepository.findTransactionById(transaction.getId());
        return true;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long transactionNumber) {
        return null;
    }
}
