package com.company.banko.service;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.Card;
import com.company.banko.domain.FinancialAccount;
import com.company.banko.domain.Person;
import com.company.banko.exeptions.CustomExeption;
import com.company.banko.model.CardDTO;
import com.company.banko.model.PersonDTO;
import com.company.banko.repository.CardRepository;
import com.company.banko.repository.FinancialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final FinancialAccountRepository financialAccountRepository;

    @Autowired
    public CardService(CardRepository cardRepository, FinancialAccountRepository financialAccountRepository) {
        this.cardRepository = cardRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    @CustomLog
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @CustomLog
    public Optional<Card> findOne(Long id) {
        return cardRepository.findById(id);
    }


    @CustomLog
    public CardDTO save(CardDTO cardDTO) throws Exception {
        Card card = new Card();
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCvv2(cardDTO.getCvv2());
        card.setIBan(cardDTO.getIBan());
        card.setExpirationDate(cardDTO.getExpirationDate());
        FinancialAccount financialAccount = financialAccountRepository.findByAccountNumber(cardDTO.getAccountNumber());
        if (!financialAccountRepository.existsById(financialAccount.id)){
            throw new CustomExeption("not found financialAccount");
        }else{
        card.setFinancialAccount(financialAccount);
        }
        cardRepository.save(card);
        return cardDTO;
    }

    public CardDTO update(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber());
        card.setIBan(cardDTO.getIBan());
        card.setCvv2(cardDTO.getCvv2());
        card.setExpirationDate(cardDTO.getExpirationDate());
        cardRepository.save(card);
        return cardDTO;
    }

    @CustomLog
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    @CustomLog
    public boolean existsById(Long id) {
        return cardRepository.existsById(id);
    }

}
