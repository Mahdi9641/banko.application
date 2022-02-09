package com.company.banko.repository;

import com.company.banko.domain.FinancialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {

    FinancialAccount findByAccountNumber(long accountNumber);
    FinancialAccount deleteFinancialAccountByAccountNumber(long accountNumber);
}
