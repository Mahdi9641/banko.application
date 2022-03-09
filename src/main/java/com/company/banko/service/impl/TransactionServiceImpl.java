package com.company.banko.service.impl;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Transaction;
import com.company.banko.exeptions.TransactionAmountCustomExeption;
import com.company.banko.model.DepositRequest;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.TransactionRepository;
import com.company.banko.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;
    private final FinancialAccountRepository financialAccountRepository;

    @Autowired
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
    @CustomLog
    public boolean insert(DepositRequest depositRequest) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(depositRequest.getAmount());
        if (depositRequest.getAmount() == null) {
            throw new TransactionAmountCustomExeption("The amount can not be empty");
        }
        transaction.setToAccount(depositRequest.getToAccount());
        transaction.setDescription(depositRequest.getDescription());
        transaction.setTransactionDate(depositRequest.getTransactionDate());
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
    @CustomLog
    public void delete(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
