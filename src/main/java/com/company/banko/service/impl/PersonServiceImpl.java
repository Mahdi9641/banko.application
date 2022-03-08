package com.company.banko.service.impl;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.Person;
import com.company.banko.model.CreatePersonRequest;
import com.company.banko.repository.PersonRepository;
import com.company.banko.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    @CustomLog
    public List<Person> findAll() {
        personRepository.findAll();
        return personRepository.findAll();
    }

    @Override
    @CustomLog
    public Person insert(CreatePersonRequest createPersonRequest) {
        Person person = new Person();
        person.setFirstName(createPersonRequest.getFirstName());
        person.setLastName(createPersonRequest.getLastName());
        person.setAge(createPersonRequest.getAge());
        person.setNationalNumber(createPersonRequest.getNationalNumber());
        person.setBirthDate(createPersonRequest.getBirthDate());
        person = personRepository.save(person);
        return person;


    }

    @Override
    @CustomLog
    public ResponseEntity<Void> delete(Long personId) {
        personRepository.deleteById(personId);
        return ResponseEntity.noContent()
                .build();

    }
}
