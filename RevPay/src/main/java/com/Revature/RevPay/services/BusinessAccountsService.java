package com.Revature.RevPay.services;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.repositories.BusinessAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessAccountsService {
    private BusinessAccountsRepository businessAccountsRepository;
    @Autowired
    public BusinessAccountsService(BusinessAccountsRepository businessAccountsRepository)
    {
        this.businessAccountsRepository = businessAccountsRepository;
    }
    public BusinessAccounts save(BusinessAccounts businessAccounts)
    {
        return businessAccountsRepository.save(businessAccounts);
    }
    public BusinessAccounts getByUsername(String username)
    {
        Optional<BusinessAccounts> result = businessAccountsRepository.findByUsername(username);
        return result.get();
    }
}
