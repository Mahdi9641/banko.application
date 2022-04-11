package com.company.banko.service.impl;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.CustomExeption;
import com.company.banko.model.CreateFinancialRequest;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.PersonRepository;
import com.company.banko.repository.TransactionRepository;
import com.company.banko.service.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
        financialAccount.setCreationDate(createFinancialRequest.getCreationDate());
        financialAccount.setDescription(createFinancialRequest.getDescription());
        financialAccount.setBalance(createFinancialRequest.getBalance());
        financialAccount.setAccountNumber(createFinancialRequest.getAccountNumber());
        Person person = personRepository.findPersonByNationalNumber(createFinancialRequest.getNationalNumber());
        if (person.getAge() < 18) {
            throw new CustomExeption("The person has not reached the legal age");
        }
        financialAccount.setPerson(person);
        financialAccount = financialAccountRepository.save(financialAccount);
        return financialAccount;
    }

    @Override
    @CustomLog
    public void delete(Long id) {
        financialAccountRepository.deleteById(id);
    }
}
