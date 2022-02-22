package com.company.banko.service;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.model.CreateFinancialRequest;
import com.company.banko.model.DepositRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FinancialAccountService {

    List<FinancialAccount> findall();

    ResponseEntity<FinancialAccount> insert(CreateFinancialRequest createFinancialRequest) throws Exception;

    FinancialAccount update(FinancialAccount financialAccount);

    FinancialAccount Insert(DepositRequest depositRequest);

    ResponseEntity<Void> delete(Long financialAccountNumber);
}
