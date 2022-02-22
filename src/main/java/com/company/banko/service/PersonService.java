package com.company.banko.service;

import com.company.banko.domain.Person;

import java.util.List;

public interface PersonService {

    List<Person> findall();

    Person insert(Person person);

    Person update(Person person);

    void delete(Long personId);
}
