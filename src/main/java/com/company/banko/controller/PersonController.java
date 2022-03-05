package com.company.banko.controller;


import com.company.banko.domain.Person;
import com.company.banko.repository.PersonRepository;
import com.company.banko.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/banko/Person-Request")
public class PersonController {

    private final PersonService personService;

    private final PersonRepository personRepository;

    public PersonController(PersonService personService, PersonRepository partyRepository) {
        this.personService = personService;
        this.personRepository = partyRepository;
    }

    @GetMapping
    private PersonService getPerson() {
        personService.findall();
        return personService;
    }

    @PostMapping
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        personService.insert(person);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "Waiting to create account");
        map.put("result", "the Person is Create");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @DeleteMapping(path = "/person/deletePerson/{personId}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long personId) {
        personService.delete(personId);
        return ResponseEntity.noContent()
                .build();
    }

}