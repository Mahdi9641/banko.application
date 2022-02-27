package com.company.banko.service;

import com.company.banko.domain.Person;
import com.company.banko.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public List<Person> findall() {
        personRepository.findAll();
        return personRepository.findAll();
    }

    @Override
    public Person insert(Person person) {
        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
        person.setAge(person.getAge());
        person.setNationalNumber(person.getNationalNumber());
        person.setBirthDate(person.getBirthDate());
        person = personRepository.save(person);
        return person;


    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long personId) {
        personRepository.deleteById(personId);
        return ResponseEntity.noContent()
                .build();

    }
}
