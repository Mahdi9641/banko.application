package com.company.banko.service;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.AccountTransactionType;
import com.company.banko.domain.CardTransaction;
import com.company.banko.domain.CardTransferTransaction;
import com.company.banko.model.CardTransactionDTO;
import com.company.banko.model.CardTransferDTO;
import com.company.banko.repository.CardRepository;
import com.company.banko.repository.CardTransactionRepository;
import com.company.banko.repository.CardTransferTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardTransferTransactionService {

    private final CardTransferTransactionRepository cardTransferTransactionRepository;
    private final CardRepository cardRepository;
    private final CardTransactionService cardTransactionService;

    @Autowired
    public CardTransferTransactionService(CardTransferTransactionRepository cardTransferTransactionRepository, CardRepository cardRepository,CardTransactionService cardTransactionService) {
        this.cardTransferTransactionRepository = cardTransferTransactionRepository;
        this.cardRepository = cardRepository;
        this.cardTransactionService = cardTransactionService;
    }

    @CustomLog
    public List<CardTransferTransaction> findAll() {
        return cardTransferTransactionRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @CustomLog
    public void delete(Long id) {
        cardTransferTransactionRepository.deleteById(id);
    }


    @Transactional
    @CustomLog
    public CardTransferTransaction handletransferFunds(CardTransferDTO cardTransferDTO) {

        CardTransactionDTO fromCardTransactionDTO = new CardTransactionDTO();
        fromCardTransactionDTO.setTransactionType(AccountTransactionType.WITHDRAWAL);
        fromCardTransactionDTO.setAmount(cardTransferDTO.getTransactionAmount());
        fromCardTransactionDTO.setDateOf(cardTransferDTO.getTransactionDate());
        fromCardTransactionDTO.setDescription(cardTransferDTO.getDescription());
        CardTransaction fromCardAccount = cardTransactionService.handleWithdraw(cardTransferDTO.getFromCardNumber(), fromCardTransactionDTO);

        CardTransactionDTO toCardTransactionDTO = new CardTransactionDTO();
        toCardTransactionDTO.setTransactionType(AccountTransactionType.DEPOSIT);
        toCardTransactionDTO.setAmount(cardTransferDTO.getTransactionAmount());
        toCardTransactionDTO.setDateOf(cardTransferDTO.getTransactionDate());
        toCardTransactionDTO.setDescription(cardTransferDTO.getDescription());
        CardTransaction toCardAccount = cardTransactionService.handleDeposit(cardTransferDTO.getToCardNumber(), toCardTransactionDTO);


        CardTransferTransaction cardTransferTransaction = new CardTransferTransaction();
        cardTransferTransaction.setDate(cardTransferDTO.getTransactionDate());
        cardTransferTransaction.setAmount(cardTransferDTO.getTransactionAmount());
        cardTransferTransaction.setDescription(cardTransferDTO.getDescription());
        cardTransferTransaction.setFromCardTransaction(fromCardAccount);
        cardTransferTransaction.setToCardTransaction(toCardAccount);
        cardTransferTransactionRepository.save(cardTransferTransaction);

        return cardTransferTransaction;

    }

}
