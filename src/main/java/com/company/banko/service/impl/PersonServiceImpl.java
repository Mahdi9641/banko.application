package com.company.banko.service.impl;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.Person;
import com.company.banko.model.PersonDTO;
import com.company.banko.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @CustomLog
    public List<Person> findAll() {
        personRepository.findAll();
        return personRepository.findAll();
    }


    @CustomLog
    public PersonDTO save(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setMiddlename(personDTO.getMiddlename());
        person.setLastName(personDTO.getLastName());
        person.setAge(personDTO.getAge());
        person.setNationalNumber(personDTO.getNationalNumber());
        person.setBirthDate(personDTO.getBirthDate());
        person.setEmailAddress(personDTO.getEmailAddress());
        person.setMobileNo(personDTO.getMobileNo());
        person = personRepository.save(person);
        return personDTO;
    }

    public PersonDTO update(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setMiddlename(personDTO.getMiddlename());
        person.setLastName(personDTO.getLastName());
        person.setAge(personDTO.getAge());
        person.setNationalNumber(personDTO.getNationalNumber());
        person.setBirthDate(personDTO.getBirthDate());
        person.setEmailAddress(personDTO.getEmailAddress());
        person.setMobileNo(personDTO.getMobileNo());
        person = personRepository.save(person);
        return personDTO;
    }

    @CustomLog
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @CustomLog
    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

}
