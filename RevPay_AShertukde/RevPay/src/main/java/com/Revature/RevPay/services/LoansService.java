package com.Revature.RevPay.services;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.Loans;
import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.repositories.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class LoansService {
    private LoansRepository loansRepository;
    private BusinessAccountsService businessAccountsService;
    private MoneyService moneyService;
    @Autowired
    public LoansService(LoansRepository loansRepository, BusinessAccountsService businessAccountsService, MoneyService moneyService) {
        this.loansRepository = loansRepository;
        this.businessAccountsService = businessAccountsService;
        this.moneyService = moneyService;
    }

    public Loans save(Loans loan, String username) {
        loan.setBusinessAccounts(businessAccountsService.getByUsername(username));
        if(loan.getAmount().compareTo(BigDecimal.ZERO) <= 0)
        {
            return null;
        }
        Money businessMoney = moneyService.getBusinessBalanceByUsername(username);
        businessMoney.setBalance(businessMoney.getBalance().add(loan.getAmount()));
        loan.setPaidback(false);
        return this.loansRepository.save(loan);
    }
    public Loans save(Loans loan)
    {
        return this.loansRepository.save(loan);
    }
    public Set<Loans> get(String username){
        BusinessAccounts businessAccounts = businessAccountsService.getByUsername(username);
        return this.loansRepository.findAllByBusinessAccounts(businessAccounts);
    }
    public Loans get(Integer id)
    {
        return this.loansRepository.findById(id).orElse(null);
    }
}
