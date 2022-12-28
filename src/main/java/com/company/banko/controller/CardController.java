package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.Card;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.BadRequestAlertException;
import com.company.banko.model.CardDTO;
import com.company.banko.model.PersonDTO;
import com.company.banko.repository.CardRepository;
import com.company.banko.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;
@RestController
@RequestMapping("/banko/card")
@SecurityRequirement(name = "basicAuth")
public class CardController {

    private final CardService cardService;
    private final CardRepository cardRepository;
    @Value("${banko.clientApp.name}")
    private String applicationName;

    @Autowired
    public CardController(CardService cardService, CardRepository cardRepository) {
        this.cardService = cardService;
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.findAll();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
    @Operation(summary = "Get a card by its id")
    @GetMapping("/getCard/{id}")
    public Optional<Card> getCard(@Parameter(description = "id of card to be searched") @PathVariable Long id) {
        Optional<Card> card = cardService.findOne(id);
        return card;
    }

    @PostMapping
    public ResponseEntity<CardDTO> createCard(@Valid @RequestBody CardDTO cardDTO) throws Exception {
        if (cardDTO.getId() == null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CardDTO result = cardService.save(cardDTO);
        return ResponseEntity.created(new URI("/banko/card" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<CardDTO> updateCard(@Valid @RequestBody CardDTO cardDTO) throws URISyntaxException {
        if (cardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!cardService.existsById(cardDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        CardDTO result = cardService.update(cardDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cardDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping(path = "{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
