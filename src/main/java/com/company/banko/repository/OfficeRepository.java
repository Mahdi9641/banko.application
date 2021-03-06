package com.company.banko.repository;

import com.company.banko.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office , Long> {

    Office findByExternalId(String externalId);

}
