package com.company.banko.repository;

import com.company.banko.domain.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {

    FinancialAccount findByAccountNumber(String accountNumber);
    FinancialAccount deleteFinancialAccountByAccountNumber(String accountNumber);

}
