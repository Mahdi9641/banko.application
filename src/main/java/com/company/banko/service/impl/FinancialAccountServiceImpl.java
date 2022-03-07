package com.company.banko.service.impl;

import com.company.banko.aop.CustomLog;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.PersonAgeCustomExeption;
import com.company.banko.model.CreateFinancialRequest;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.PersonRepository;
import com.company.banko.repository.TransactionRepository;
import com.company.banko.service.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FinancialAccountServiceImpl implements FinancialAccountService {


    private final FinancialAccountRepository financialAccountRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public FinancialAccountServiceImpl(FinancialAccountRepository financialAccountRepository, PersonRepository personRepository, TransactionRepository transactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    @CustomLog
    public List<FinancialAccount> findAll() {
        financialAccountRepository.findAll();
        return financialAccountRepository.findAll();
    }

    @Override
    @CustomLog
    public FinancialAccount insert(CreateFinancialRequest createFinancialRequest) throws Exception {
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setCreationDate(financialAccount.getCreationDate());
        financialAccount.setDescription(financialAccount.getDescription());
        financialAccount.setBalance(financialAccount.getBalance());
        financialAccount.setAccountNumber(financialAccount.getAccountNumber());
        Person person = personRepository.findPersonByNationalNumber(createFinancialRequest.getNationalNumber());
        if (person.getAge() < 18) {
            throw new PersonAgeCustomExeption("The person has not reached the legal age");
        }
        financialAccount.setPerson(person);
        System.out.println("financialAccount.toString()" + financialAccount.toString());
        financialAccount = financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @Override
    @CustomLog
    public void delete(Long financialAccountNumber) {
        financialAccountRepository.deleteFinancialAccountByAccountNumber(financialAccountNumber);
    }
}
