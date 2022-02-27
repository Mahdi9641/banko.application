package com.company.banko.service;

import com.company.banko.domain.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    List<Person> findall();

    Person insert(Person person);

    Person update(Person person);

    ResponseEntity<Void> delete(Long personId);
}
