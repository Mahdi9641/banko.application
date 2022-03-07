package com.company.banko.service;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.model.CreateFinancialRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FinancialAccountService {

    List<FinancialAccount> findAll();

    FinancialAccount insert(CreateFinancialRequest createFinancialRequest) throws Exception;

    void delete(Long financialAccountNumber);
}
