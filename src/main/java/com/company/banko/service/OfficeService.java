package com.company.banko.service;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.controller.OfficeController;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Office;
import com.company.banko.model.OfficeDTO;
import com.company.banko.repository.OfficeRepository;
import io.swagger.annotations.Authorization;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(OfficeController.class);

    @CustomLog
    public List<Office> findAll() {
        officeRepository.findAll();
        return officeRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));
    }


    @CustomLog
    public Optional<Office> findOne(Long id) {
        return officeRepository.findById(id);
    }

    @CustomLog
    public OfficeDTO save(OfficeDTO officeDTO){
        Office office = new Office();
        office.setName(officeDTO.getName());
        office.setExternalId(officeDTO.getExternalId());
        office.setOpeningDate(officeDTO.getOpeningDate());
        office.setParent(fromId(officeDTO.getParentid()));
        office = officeRepository.save(office);
        log.info(office.toString());
        return officeDTO;

    }

    @CustomLog
    public OfficeDTO update(OfficeDTO officeDTO){

        if (officeDTO == null) {
            return null;
        }
        Office office = officeRepository.findByExternalId(officeDTO.getExternalId());
        office.setName(officeDTO.getName());
        office.setOpeningDate(officeDTO.getOpeningDate());
        office.setParent(fromId(officeDTO.getParentid()));
        office = officeRepository.save(office);
        return officeDTO;

    }

    @CustomLog
    public void delete(Long id) {
        officeRepository.deleteById(id);
    }

    @CustomLog
    public boolean existsById(Long id) {
        return officeRepository.existsById(id);
    }

    Office fromId(Long id) {
        if (id == null){
            return null;
        }
        Office office = new Office();
        office.setId(id);
        return office;

    }

}
