package com.company.banko.service;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.CustomExeption;
import com.company.banko.model.AccountDTO;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.PersonRepository;
import com.company.banko.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class FinancialAccountServiceImpl {


    private final FinancialAccountRepository financialAccountRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;


    @Autowired
    public FinancialAccountServiceImpl(FinancialAccountRepository financialAccountRepository, PersonRepository personRepository, TransactionRepository transactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
    }


    @CustomLog
    public List<FinancialAccount> findAll() {
        financialAccountRepository.findAll();
        return financialAccountRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @CustomLog
    public Optional<FinancialAccount> findOne(Long id) {
        return financialAccountRepository.findById(id);
    }

    @CustomLog
    public AccountDTO save(AccountDTO accountDTO) throws Exception {
        FinancialAccount financialAccount = new FinancialAccount();
        financialAccount.setCreationDate(accountDTO.getCreationDate());
        financialAccount.setDescription(accountDTO.getDescription());
        financialAccount.setBalance(accountDTO.getBalance());
        financialAccount.setAccountNumber(accountDTO.getAccountNumber());
        financialAccount.setStatus(accountDTO.getStatus());
        Person person = personRepository.findPersonByNationalNumber(accountDTO.getPersonNationalNumber());
        if (person.getAge() < 18) {
            throw new CustomExeption("The person has not reached the legal age");
        }
        financialAccount.setPerson(person);
        financialAccount = financialAccountRepository.save(financialAccount);
        return accountDTO;
    }


    public AccountDTO update(AccountDTO accountDTO) throws Exception {
        if (accountDTO == null) {
            return null;
        }
        FinancialAccount account = financialAccountRepository.findByAccountNumber(accountDTO.getAccountNumber());
        account.setId(accountDTO.getId());
        account.setStatus(accountDTO.getStatus());
        account.setDescription(accountDTO.getDescription());
        account = financialAccountRepository.save(account);
        return accountDTO;
    }

    @CustomLog
    public void delete(Long id) {
        financialAccountRepository.deleteById(id);
    }

    @CustomLog
    public boolean existsById(Long id) {
        return financialAccountRepository.existsById(id);
    }

}
