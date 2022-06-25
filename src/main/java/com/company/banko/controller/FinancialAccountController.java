package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.exeptions.BadRequestAlertException;
import com.company.banko.model.AccountDTO;
import com.company.banko.service.FinancialAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/banko")
public class FinancialAccountController {

    private static final String ENTITY_NAME = "financialAccount";
    @Autowired
    private FinancialAccountServiceImpl financialAccountService;
    @Value("${banko.clientApp.name}")
    private String applicationName;

    @GetMapping(path = "/account/getFinancialAccount")
    public ResponseEntity <List<FinancialAccount>> getAllFinancialAccounts() {
        List<FinancialAccount> financialAccounts = financialAccountService.findAll();
        return new ResponseEntity<>(financialAccounts, HttpStatus.OK);
    }

    @GetMapping("/account/getFinancialAccount/{id}")
    public Optional<FinancialAccount> getFinancialAccount(@PathVariable Long id) {
        Optional<FinancialAccount> financialAccount = financialAccountService.findOne(id);
        return financialAccount;
    }


    @PostMapping(path = "/account/createFinancialAccount")
    public ResponseEntity<Object> createFinancialAccount(@Valid @RequestBody AccountDTO accountDTO) throws Exception {
        if (accountDTO.getId() == null) {
            throw new BadRequestAlertException("A new savingsAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        financialAccountService.save(accountDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "active");
        map.put("result", "the Account is Create");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/account/UpdateFinancialAccount")
    public ResponseEntity<AccountDTO> updateFinancialAccount(@Valid @RequestBody AccountDTO accountDTO) throws Exception {
        if (accountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!financialAccountService.existsById(accountDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        financialAccountService.update(accountDTO);
        AccountDTO result = financialAccountService.update(accountDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountDTO.getId().toString()))
                .body(result);
    }


    @DeleteMapping(path = "/account/deleteFinancialAccount/{id}")
    public ResponseEntity<Void> deleteFinancialAccount(@PathVariable Long id) {
        financialAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}


