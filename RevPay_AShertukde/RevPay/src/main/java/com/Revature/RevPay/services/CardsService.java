package com.Revature.RevPay.services;

import com.Revature.RevPay.models.Cards;
import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CardsService {
    private MoneyService moneyService;
    private CardsRepository cardsRepository;
    @Autowired
    public CardsService(MoneyService moneyService, CardsRepository cardsRepository)
    {
        this.moneyService = moneyService;
        this.cardsRepository = cardsRepository;
    }
    public Cards save(Cards card, String username)
    {
        card.setMoney(moneyService.getUserBalanceByUsername(username));
        return cardsRepository.save(card);
    }
    public Cards saveBusiness(Cards card, String username)
    {
        card.setMoney(moneyService.getBusinessBalanceByUsername(username));
        return cardsRepository.save(card);
    }

    public Set<Cards> getCard(String username) {
        Money money = moneyService.getUserBalanceByUsername(username);
        return cardsRepository.findCardsByMoney(money);
    }

    public Set<Cards> getBusinessCard(String username) {
        Money money = moneyService.getBusinessBalanceByUsername(username);
        return cardsRepository.findCardsByMoney(money);
    }
}
