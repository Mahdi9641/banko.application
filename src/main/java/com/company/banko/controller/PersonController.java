package com.company.banko.controller;


import com.company.banko.domain.Person;
import com.company.banko.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/banko")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(Person.class);

    private final PersonRepository personRepository;

    public PersonController(PersonRepository partyRepository) {
        this.personRepository = partyRepository;
    }

  @GetMapping("/getparty")
  private Person getParty(){
      log.info("getParty");
      Person person = new Person();
        personRepository.findAll();
        return person;
  }
    @PostMapping("/createparty")
    public Person creatParty(@RequestBody Person person){
        log.info("createPerson");
        personRepository.save(person);
        return person;
    }
    @PutMapping("/updateParty")
    public Person updateParty(@RequestBody Person person){
        log.info("updatePerson");
        personRepository.save(person);
        return person;
   }
    @DeleteMapping("/deleteParty")
    public Person deleteParty(@RequestBody Person person){
        log.info("deleteParty");
        personRepository.deleteById(person.getId());
        personRepository.save(person);
        return person;
    }

}
