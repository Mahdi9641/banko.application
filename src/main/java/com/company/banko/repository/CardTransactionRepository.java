package com.company.banko.repository;

import com.company.banko.domain.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTransactionRepository extends JpaRepository<CardTransaction , Long> {


}
