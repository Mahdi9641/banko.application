package com.company.banko.service;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.Person;
import com.company.banko.model.PersonDTO;
import com.company.banko.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl {

    private final PersonRepository personRepository;
    private final OfficeService officeService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, OfficeService officeService) {
        this.personRepository = personRepository;
        this.officeService = officeService;
    }


    @CustomLog
    public List<Person> findAll() {
        return personRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @CustomLog
    public Optional<Person> findOne(Long id) {
        return personRepository.findById(id);
    }


    @CustomLog
    public PersonDTO save(PersonDTO personDTO) {
        Person person = new Person();
        person.setOffice(officeService.fromId(personDTO.getOfficeId()));
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
        Person person = personRepository.findPersonByNationalNumber(personDTO.getNationalNumber());
        person.setFirstName(personDTO.getFirstName());
        person.setMiddlename(personDTO.getMiddlename());
        person.setLastName(personDTO.getLastName());
        person.setAge(personDTO.getAge());
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
