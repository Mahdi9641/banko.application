package com.company.banko.controller;

import com.company.banko.config.HeaderUtil;
import com.company.banko.domain.Office;
import com.company.banko.exeptions.BadRequestAlertException;
import com.company.banko.model.OfficeDTO;
import com.company.banko.service.OfficeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "basicAuth")
public class OfficeController {

    private static final String ENTITY_NAME = "Office";

    @Autowired
    private OfficeService officeService;

    @Value("${banko.clientApp.name}")
    private String applicationName;


    @GetMapping(path = "/office/getAlloffice")
    public ResponseEntity<List<Office>> getAllOffice(){
        List<Office> offices = officeService.findAll();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @GetMapping(path = "/office/getoffice/{id}")
    public Optional<Office> getOffice(@PathVariable Long id) {
        Optional<Office> office = officeService.findOne(id);
        return office;
    }

    @PostMapping(path = "/office/createOffice")
    public ResponseEntity<Object> createOffice(@Valid @RequestBody OfficeDTO officeDTO) throws Exception {
        if (officeDTO.getId() == null) {
            throw new BadRequestAlertException("A new savingsAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        officeService.save(officeDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "active");
        map.put("result", "the Account is Create");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @PutMapping("/office/UpdateOffice")
    public ResponseEntity<OfficeDTO> updateOffice(@Valid @RequestBody OfficeDTO officeDTO) throws Exception {
        if (officeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!officeService.existsById(officeDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        officeService.update(officeDTO);
        OfficeDTO result = officeService.update(officeDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, officeDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/office/DeleteOffice")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id) {
        officeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }



}
