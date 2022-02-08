package com.company.banko.controller;

import com.company.banko.domain.Party;
import com.company.banko.repository.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/banko")
public class PartyController {

    private static final Logger log = LoggerFactory.getLogger(Party.class);

    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

  @GetMapping("/getparty")
  private Party getParty(){
      log.info("getParty");
        Party party = new Party();
        partyRepository.findAll();
        return party;
  }
    @PostMapping("/createparty")
    public Party creatParty(@RequestBody Party party){
        log.info("creatParty");
        partyRepository.save(party);
        return party;
    }
    @PutMapping("/updateParty")
    public Party updateParty(@RequestBody Party party){
        log.info("updateParty");
        partyRepository.save(party);
        return party;
   }
    @DeleteMapping("/deleteParty")
    public Party deleteParty(@RequestBody Party party){
        log.info("deleteParty");
        partyRepository.deleteById(party.getId());
        partyRepository.save(party);
        return party;
    }

}
