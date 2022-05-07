package com.company.banko.service.impl;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.AccountTransactionType;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.FinancialAccountStatusType;
import com.company.banko.domain.Transaction;
import com.company.banko.exeptions.SavingsAccountBlockedException;
import com.company.banko.exeptions.SavingsAccountNotFoundException;
import com.company.banko.model.AccountTransactionDTO;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl {


    private final TransactionRepository transactionRepository;
    private final FinancialAccountRepository financialAccountRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, FinancialAccountRepository financialAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    @CustomLog
    public List<Transaction> findAll() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @CustomLog
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }


    @Transactional
    @CustomLog
    public Transaction handleDeposit(Long savingsId, AccountTransactionDTO accountTransactionDTO) {

        if (!financialAccountRepository.existsById(savingsId)) {
            throw new SavingsAccountNotFoundException("Savings account not found", savingsId.toString(), "idnotfound");
        }
        Transaction transaction;
        Optional<FinancialAccount> retFinancialAccount = financialAccountRepository.findById(savingsId);
        if (retFinancialAccount.isPresent()) {
            FinancialAccount financialAccount = retFinancialAccount.get();
            validateForAccountBlock(financialAccount);

            transaction = new Transaction();
            transaction.setFinancialAccount(financialAccount);
            transaction.setId(accountTransactionDTO.getId());
            transaction.setAmount(accountTransactionDTO.getAmount());
            transaction.setTransactionType(AccountTransactionType.DEPOSIT);
            transaction.setDescription(accountTransactionDTO.getDescription());
            transaction.setTransactionDate(accountTransactionDTO.getDateOf());
            transaction = transactionRepository.save(transaction);

            financialAccount.setBalance(accountTransactionDTO.getAmount().add(financialAccount.getBalance()));
            financialAccountRepository.save(financialAccount);


        } else {
            throw new SavingsAccountNotFoundException("Savings account not found", savingsId.toString(), "accountnotfound");
        }
        return transaction;
    }


    @Transactional
    @CustomLog
    public Transaction handleWithdraw(Long savingsId, AccountTransactionDTO accountTransactionDTO) {
        if (!financialAccountRepository.existsById(savingsId)) {
            throw new SavingsAccountNotFoundException("Savings account not found", savingsId.toString(), "idnotfound");
        }

        Transaction transaction;

        Optional<FinancialAccount> retFinancialAccount = financialAccountRepository.findById(savingsId);
        if (retFinancialAccount.isPresent()) {
            FinancialAccount financialAccount = retFinancialAccount.get();
            validateForAccountBlock(financialAccount);

            transaction = new Transaction();
            transaction.setFinancialAccount(financialAccount);
            transaction.setAmount(accountTransactionDTO.getAmount());
            transaction.setTransactionType(AccountTransactionType.WITHDRAWAL);
            transaction.setDescription(accountTransactionDTO.getDescription());
            transaction.setTransactionDate(accountTransactionDTO.getDateOf());

            transaction = transactionRepository.save(transaction);

            financialAccount.setBalance(financialAccount.getBalance().subtract(accountTransactionDTO.getAmount()));
            financialAccountRepository.save(financialAccount);


        } else {
            throw new SavingsAccountNotFoundException("Savings account not found", savingsId.toString(), "accountnotfound");
        }
        return transaction;
    }

    private void validateForAccountBlock(FinancialAccount financialAccount) {
        final FinancialAccountStatusType currentStatus = financialAccount.getStatus();
        if (FinancialAccountStatusType.BLOCK.equals(currentStatus)) {
            throw new SavingsAccountBlockedException("Savings account blocked", financialAccount.getId().toString(), "accountblocked");
        }
    }


    Transaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }

    Transaction toId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}


