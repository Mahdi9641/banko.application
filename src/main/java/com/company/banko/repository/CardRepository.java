package com.company.banko.repository;

import com.company.banko.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String cardNumber);

    @Query("select c from Card c where c.cardNumber = :cardNmber")
    Optional<Card> findCard(@Param("cardNmber") String cardNmber);

    boolean existsByCardNumber(String cardNumber);
}
