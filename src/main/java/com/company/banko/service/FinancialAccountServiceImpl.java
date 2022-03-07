package com.company.banko.service;

import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.PersonAgeCustomExeption;
import com.company.banko.model.CreateFinancialRequest;
import com.company.banko.repository.FinancialAccountRepository;
import com.company.banko.repository.PersonRepository;
import com.company.banko.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FinancialAccountServiceImpl implements FinancialAccountService {

    @Autowired
    private final FinancialAccountRepository financialAccountRepository;

    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final TransactionRepository transactionRepository;

    public FinancialAccountServiceImpl(FinancialAccountRepository financialAccountRepository, PersonRepository personRepository, TransactionRepository transactionRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<FinancialAccount> findall() {
        financialAccountRepository.findAll();
        return financialAccountRepository.findAll();
    }

    @Override
    public ResponseEntity<FinancialAccount> insert(CreateFinancialRequest createFinancialRequest) throws Exception {
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
        return ResponseEntity.ok().body(financialAccount);
    }

    @Override
    public FinancialAccount update(FinancialAccount financialAccount) {
        return null;
    }

  /*  @Override
    public boolean Insert(DepositRequest depositRequest) {
        FinancialAccount financialAccount = new FinancialAccount();
        Transaction transaction = new Transaction();
        transaction.setAmount(depositRequest.getAmount());
        FinancialAccount account = financialAccountRepository.findByAccountNumber(depositRequest.getAccountNumber());
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        Transaction tr = transactionRepository.save(transaction);
        System.out.println("tr.toString() = " + tr.toString()); //for see detail
        financialAccount = financialAccountRepository.findByAccountNumber(account.getAccountNumber());
        return true;
    }*/


    @Override
    public ResponseEntity<Void> delete(Long financialAccountNumber) {

        financialAccountRepository.deleteFinancialAccountByAccountNumber(financialAccountNumber);
        return ResponseEntity.noContent()
                .build();
    }
}
