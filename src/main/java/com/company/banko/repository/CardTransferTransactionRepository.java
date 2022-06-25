package com.company.banko.repository;

import com.company.banko.domain.CardTransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTransferTransactionRepository extends JpaRepository< CardTransferTransaction , Long> {

}
