package com.company.banko.service;

import com.company.banko.CustomAnnotation.CustomLog;
import com.company.banko.domain.*;
import com.company.banko.exeptions.SavingsAccountBlockedException;
import com.company.banko.exeptions.SavingsAccountNotFoundException;
import com.company.banko.model.AccountTransactionDTO;
import com.company.banko.model.CardTransactionDTO;
import com.company.banko.repository.CardRepository;
import com.company.banko.repository.CardTransactionRepository;
import com.company.banko.repository.FinancialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardTransactionService {

    private final CardTransactionRepository cardTransactionRepository;
    private final CardRepository cardRepository;
    private final FinancialAccountRepository financialAccountRepository;

    @Autowired
    public CardTransactionService(CardTransactionRepository cardTransactionRepository, CardRepository cardRepository, FinancialAccountRepository financialAccountRepository) {
        this.cardTransactionRepository = cardTransactionRepository;
        this.cardRepository = cardRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    @CustomLog
    public List<CardTransaction> findAll() {
        return cardTransactionRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @CustomLog
    public void delete(Long id) {
        cardTransactionRepository.deleteById(id);
    }

    @Transactional
    @CustomLog
    public CardTransaction handleDeposit(String cardNumber, CardTransactionDTO cardTransactionDTO) {

        if (!cardRepository.existsByCardNumber(cardNumber)) {
            throw new SavingsAccountNotFoundException("Card not found", cardNumber, "idnotfound");
        }
        CardTransaction cardTransaction;
        Optional<Card> card = cardRepository.findCard(cardNumber);
        if (card.isPresent()) {
            Card card1 = card.get();
            validateForAccountBlock(card1.getFinancialAccount());

            cardTransaction = new CardTransaction();
            cardTransaction.setCard(card1);
            cardTransaction.setId(cardTransactionDTO.getId());
            cardTransaction.setAmount(cardTransactionDTO.getAmount());
            cardTransaction.setTransactionType(AccountTransactionType.DEPOSIT);
            cardTransaction.setDescription(cardTransactionDTO.getDescription());
            cardTransaction.setTransactionDate(cardTransactionDTO.getDateOf());
            cardTransaction = cardTransactionRepository.save(cardTransaction);

            FinancialAccount financialAccount = financialAccountRepository.getById(card1.getFinancialAccount().getId());
            financialAccount.setBalance(cardTransactionDTO.getAmount().add(financialAccount.getBalance()));//setBalance(accountTransactionDTO.getAmount().add(financialAccount.getBalance()));
            financialAccountRepository.save(financialAccount);


        } else {
            throw new SavingsAccountNotFoundException("Savings account not found", cardNumber, "accountnotfound");
        }
        return cardTransaction;
    }


    @Transactional
    @CustomLog
    public CardTransaction handleWithdraw(String cardNumber, CardTransactionDTO cardTransactionDTO) {

        if (!cardRepository.existsByCardNumber(cardNumber)) {
            throw new SavingsAccountNotFoundException("Card not found", cardNumber, "idnotfound");
        }
        CardTransaction cardTransaction;
        Optional<Card> card = Optional.ofNullable(cardRepository.findByCardNumber(cardNumber));
        if (card.isPresent()) {
            Card card1 = card.get();
            validateForAccountBlock(card1.getFinancialAccount());

            cardTransaction = new CardTransaction();
            cardTransaction.setCard(card1);
            cardTransaction.setId(cardTransactionDTO.getId());
            cardTransaction.setAmount(cardTransactionDTO.getAmount());
            cardTransaction.setTransactionType(AccountTransactionType.WITHDRAWAL);
            cardTransaction.setDescription(cardTransactionDTO.getDescription());
            cardTransaction.setTransactionDate(cardTransactionDTO.getDateOf());
            cardTransaction = cardTransactionRepository.save(cardTransaction);

            FinancialAccount financialAccount = financialAccountRepository.getById(card1.getFinancialAccount().getId());
            financialAccount.setBalance(cardTransactionDTO.getAmount().subtract(financialAccount.getBalance()));
            financialAccountRepository.save(financialAccount);


        } else {
            throw new SavingsAccountNotFoundException("Savings account not found", cardNumber, "accountnotfound");
        }
        return cardTransaction;
    }

    private void validateForAccountBlock(FinancialAccount financialAccount) {
        final FinancialAccountStatusType currentStatus = financialAccount.getStatus();
        if (FinancialAccountStatusType.BLOCK.equals(currentStatus)) {
            throw new SavingsAccountBlockedException("Savings account blocked", financialAccount.getId().toString(), "accountblocked");
        }
    }


    CardTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        CardTransaction cardTransaction = new CardTransaction();
        cardTransaction.setId(id);
        return cardTransaction;
    }

    CardTransaction toId(Long id) {
        if (id == null) {
            return null;
        }
        CardTransaction cardTransaction = new CardTransaction();
        cardTransaction.setId(id);
        return cardTransaction;
    }

}
