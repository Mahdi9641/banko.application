package com.company.banko.repository;

import com.company.banko.domain.AccountTransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransferTransactionRpository extends JpaRepository<AccountTransferTransaction, Long> {
}
