package com.company.banko.controller;


import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.BadRequestAlertException;
import com.company.banko.model.PersonDTO;
import com.company.banko.repository.PersonRepository;
import com.company.banko.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;


@RestController
@RequestMapping("/banko/Person-Request")
public class PersonController {

    private final PersonServiceImpl personService;
    private final PersonRepository personRepository;
    @Value("${banko.clientApp.name}")
    private String applicationName;

    public PersonController(PersonServiceImpl personService, PersonRepository partyRepository) {
        this.personService = personService;
        this.personRepository = partyRepository;
    }

    @GetMapping
    public ResponseEntity <List<Person>> getAllClients() {
        List<Person> personList = personService.findAll();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createClient(@Valid @RequestBody PersonDTO personDTO) throws URISyntaxException {
        if (personDTO.getId() == null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonDTO result = personService.save(personDTO);
        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updateClient(@Valid @RequestBody PersonDTO personDTO) throws URISyntaxException {
        if (personDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!personService.existsById(personDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        PersonDTO result = personService.update(personDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping(path = "{personId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

