package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.AccountTransferTransaction;
import com.company.banko.model.AccountTransferDTO;
import com.company.banko.service.impl.TransferServiceImpl;
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
@RequestMapping("/banko/Transfer")
public class TransferController {

    private final TransferServiceImpl transferService;
    @Value("${banko.clientApp.name}")
    private String applicationName;

    @Autowired
    public TransferController(TransferServiceImpl transferService) {
        this.transferService = transferService;
    }


    @GetMapping
        public ResponseEntity<List<AccountTransferTransaction>> getAllSavingsAccountTransactions() {
        List<AccountTransferTransaction> transactions = transferService.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Object> createTransaction(@RequestBody AccountTransferDTO accountTransferDTO) throws Exception {
        transferService.handletransferFunds(accountTransferDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("TYPE", "DEPOSIT");
        map.put("RESULT", " The amount was deposit of  " + accountTransferDTO.getTransactionAmount() +"  from account ID  " + accountTransferDTO.getFromAccountId() +  "  to the account ID  " + accountTransferDTO.getToAccountId());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/account/deleteAccountTransferTransaction/{id}")
    public ResponseEntity<Void> deleteSavingsAccount(@PathVariable Long id) {
        transferService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
