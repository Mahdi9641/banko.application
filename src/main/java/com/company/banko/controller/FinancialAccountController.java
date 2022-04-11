package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.model.CreateFinancialRequest;
import com.company.banko.service.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banko")
public class FinancialAccountController {

    private static final String ENTITY_NAME = "financialAccount";
    @Autowired
    private FinancialAccountService financialAccountService;
    @Value("${banko.clientApp.name}")
    private String applicationName;

    @GetMapping(path = "/account/getFinancialAccount")
    public ResponseEntity<List<FinancialAccount>> getFinancialAccounts() {
        List<FinancialAccount> financialAccounts = financialAccountService.findAll();
        return new ResponseEntity<>(financialAccounts, HttpStatus.OK);
    }

    @PostMapping(path = "/account/createFinancialAccount")
    private ResponseEntity<Object> createFinancialAccount(@RequestBody CreateFinancialRequest createFinancialRequest) throws Exception {
        financialAccountService.insert(createFinancialRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "active");
        map.put("result", "the Account is Create");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/account/deleteFinancialAccount/{id}")
    private ResponseEntity<FinancialAccount> deleteFinancialAccount(@PathVariable Long id) {
        financialAccountService.delete(id);
     /*   return ResponseEntity.noContent()
                .build();*/
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}


