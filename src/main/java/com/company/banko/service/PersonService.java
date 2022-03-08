package com.company.banko.service;

import com.company.banko.domain.Person;
import com.company.banko.model.CreatePersonRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person insert(CreatePersonRequest createPersonRequest);

    ResponseEntity<Void> delete(Long personId);
}
