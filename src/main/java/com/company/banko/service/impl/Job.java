package com.company.banko.service.impl;

import com.company.banko.domain.AccountTransactionType;
import com.company.banko.domain.AccountTransferTransaction;
import com.company.banko.domain.Transaction;
import com.company.banko.model.AccountTransactionDTO;
import com.company.banko.repository.AccountTransferTransactionRpository;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.TransactionRepository;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Logger;

@EnableAsync
@EnableScheduling
@Service
@Transactional
public class Job {

    static final Logger LOGGER = Logger.getLogger(Job.class.getName());
    private final FinancialAccountRepository financialAccountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionServiceImpl transactionService;
    private final AccountTransferTransactionRpository accountTransferTransactionRepository;
    private Double price;


    @Autowired
    public Job(FinancialAccountRepository financialAccountRepository, TransactionRepository transactionRepository, TransactionServiceImpl transactionService, AccountTransferTransactionRpository accountTransferTransactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;

        this.accountTransferTransactionRepository = accountTransferTransactionRepository;
    }


    @Scheduled(cron = "0 15 10 15 * ?", zone = "Asia/Tehran")
    @Async
    @Bean
    public AccountTransferTransaction transfer() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);

        AccountTransactionDTO fromAccountTransactionDTO = new AccountTransactionDTO();
        fromAccountTransactionDTO.setTransactionType(AccountTransactionType.WITHDRAWAL);
        fromAccountTransactionDTO.setAmount(BigDecimal.valueOf(20));
        fromAccountTransactionDTO.setDateOf(new Date());
        fromAccountTransactionDTO.setDescription("انتقال از حساب");
        Transaction fromSavingsAccount = transactionService.handleWithdraw(1l, fromAccountTransactionDTO);

        AccountTransactionDTO toAccountTransactionDTO = new AccountTransactionDTO();
        toAccountTransactionDTO.setTransactionType(AccountTransactionType.DEPOSIT);
        toAccountTransactionDTO.setAmount(BigDecimal.valueOf(20));
        toAccountTransactionDTO.setDateOf(new Date());
        toAccountTransactionDTO.setDescription("انتقال به حساب");
        Transaction toSavingsAccount = transactionService.handleDeposit(102l, toAccountTransactionDTO);
        ;

        AccountTransferTransaction accountTransferTransaction = new AccountTransferTransaction();
        accountTransferTransaction.setDate(new Date());
        accountTransferTransaction.setAmount(BigDecimal.valueOf(20));
        accountTransferTransaction.setDescription("انتقال حساب به حساب");
        accountTransferTransaction.setFromSavingsTransaction(fromSavingsAccount);
        accountTransferTransaction.setToSavingsTransaction(toSavingsAccount);
        accountTransferTransactionRepository.save(accountTransferTransaction);
        return accountTransferTransaction;
    }


}
