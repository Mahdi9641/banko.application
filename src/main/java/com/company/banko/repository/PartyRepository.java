package com.company.banko.repository;

import com.company.banko.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    Party findPartyByNationalNumber(long id);
}