package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.services.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoneyController {
    private MoneyService moneyService;

    @Autowired
    public MoneyController(MoneyService moneyService)
    {
        this.moneyService = moneyService;
    }
    public MoneyController(){ }
    @PostMapping(path = "/user_moneybyid")
    @ResponseStatus(HttpStatus.OK)
    public Money addUserBalanceById(@RequestBody Money money, @RequestParam Integer id)
    {
        UserAccounts userAccounts = new UserAccounts(id);
        money.setUserAccounts(userAccounts);
        Money result = moneyService.saveBalanceById(money);
        result.setUserAccounts(null);
        return result;
    }
    @PostMapping(path = "/user_moneybyusername")
    @ResponseStatus(HttpStatus.OK)
    public Money addUserBalanceByUsername(@RequestBody Money money, @RequestParam String username)
    {
        UserAccounts userAccounts = new UserAccounts(username);
        money.setUserAccounts(userAccounts);
        Money result = moneyService.saveBalanceByUsername(money);
        result.setUserAccounts(null);
        return result;
    }
    @PostMapping(path = "/business_moneybyusername")
    @ResponseStatus(HttpStatus.OK)
    public Money addBusinessBalanceByUsername(@RequestBody Money money, @RequestParam String username)
    {
        BusinessAccounts businessAccounts = new BusinessAccounts(username);
        money.setBusinessAccounts(businessAccounts);
        Money result = moneyService.saveBusinessBalanceByUsername(money);
        result.setBusinessAccounts(null);
        return result;
    }
}
