package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.CardTransferTransaction;
import com.company.banko.model.AccountTransferDTO;
import com.company.banko.model.CardTransferDTO;
import com.company.banko.service.CardTransferTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/banko/Cardtransfer")
public class CardTransferController {

    private final CardTransferTransactionService cardTransferTransactionService;
    @Value("${banko.clientApp.name}")
    private String applicationName;

    @Autowired
    public CardTransferController(CardTransferTransactionService cardTransferTransactionService) {
        this.cardTransferTransactionService = cardTransferTransactionService;
    }


    @GetMapping
    public ResponseEntity<List<CardTransferTransaction>> getAllTransfers() {
        List<CardTransferTransaction> transactions = cardTransferTransactionService.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Object> createTransfer(@RequestBody CardTransferDTO cardTransferDTO) throws Exception {
        cardTransferTransactionService.handletransferFunds(cardTransferDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("TYPE", "DEPOSIT");
        map.put("RESULT", " The amount was deposit of  " + cardTransferDTO.getTransactionAmount() +"  from Card ID  " + cardTransferDTO.getFromCardId() +  "  to Card ID  " + cardTransferDTO.getToCardId());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/account/deleteCardTransferTransaction/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        cardTransferTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}
