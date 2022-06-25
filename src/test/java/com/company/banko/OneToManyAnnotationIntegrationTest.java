package com.company.banko;


import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Transaction;
import com.company.banko.repository.FinancialAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class OneToManyAnnotationIntegrationTest {
//
//    @Autowired
//    private FinancialAccountRepository financialAccountRepository;
//
//
//    @Test
//    public void givenManyTransaction_WhenInsertAccount_ThenCreats1toManyRelationShip() {
//
//        FinancialAccount financialAccount = new FinancialAccount(1l, 123456l);
//
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(1l, new BigDecimal(30000), "entegal"));
//        //transactions.add(new Transaction(2l , 40000 ,"entegal" ));
//
//        financialAccount.setTransactions(transactions);
//        financialAccountRepository.save(financialAccount);
//    }
//
//}
