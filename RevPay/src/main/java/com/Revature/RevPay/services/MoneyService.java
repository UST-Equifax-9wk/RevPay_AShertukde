package com.Revature.RevPay.services;

import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.repositories.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {
    private UserAccountsService userAccountsService;
    private BusinessAccountsService businessAccountsService;
    private MoneyRepository moneyRepository;

    public MoneyService() { }
    @Autowired
    public MoneyService(UserAccountsService userAccountsService, MoneyRepository moneyRepository, BusinessAccountsService businessAccountsService) {
        this.userAccountsService = userAccountsService;
        this.moneyRepository = moneyRepository;
        this.businessAccountsService = businessAccountsService;
    }
    public Money saveBalanceById(Money money)
    {
        money.setUserAccounts(userAccountsService.get(money.getUserAccounts().getUserid()));
        return this.moneyRepository.save(money);
    }
    public Money saveBalanceByUsername(Money money)
    {
        money.setUserAccounts(userAccountsService.getByUsername(money.getUserAccounts().getUsername()));
        return this.moneyRepository.save(money);
    }
    public Money saveBusinessBalanceByUsername(Money money)
    {
        money.setBusinessAccounts(businessAccountsService.getByUsername(money.getBusinessAccounts().getUsername()));
        return this.moneyRepository.save(money);
    }
}
