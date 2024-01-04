package com.Revature.RevPay.services;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.repositories.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    public Money updateBalanceByMoney(Money money)
    {
        return this.moneyRepository.save(money);
    }
    public Money saveBalanceByUsername(Money money)
    {
        money.setUserAccounts(userAccountsService.getByUsername(money.getUserAccounts().getUsername()));
        return this.moneyRepository.save(money);
    }
    public Money getBalanceById(Integer id)
    {
        return this.moneyRepository.findById(id).orElse(null);
    }
    public Money saveBusinessBalanceByUsername(Money money)
    {
        money.setBusinessAccounts(businessAccountsService.getByUsername(money.getBusinessAccounts().getUsername()));
        return this.moneyRepository.save(money);
    }
    public Money getUserBalanceByUsername(String username)
    {
        UserAccounts user = (userAccountsService.getByUsername(username));
        if(user == null)
        {
            return null;
        }
        return this.moneyRepository.findByUserAccounts(user).orElse(null);
    }
    public Money getUserBalanceByEmail(String email)
    {
        UserAccounts user = (userAccountsService.getByEmail(email));
        if(user == null)
        {
            return null;
        }
        return this.moneyRepository.findByUserAccounts(user).orElse(null);
    }
    public Money getUserBalanceByPhone(String phonenumber)
    {
        UserAccounts user = (userAccountsService.getByPhoneNumber(phonenumber));
        if(user == null)
        {
            return null;
        }
        return this.moneyRepository.findByUserAccounts(user).orElse(null);
    }
    public Money getBusinessBalanceByUsername(String username)
    {
        BusinessAccounts business = businessAccountsService.getByUsername(username);
        if(business == null)
        {
            return null;
        }
        return this.moneyRepository.findByBusinessAccounts(business).orElse(null);
    }
}
