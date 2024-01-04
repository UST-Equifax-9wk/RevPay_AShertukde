package com.Revature.RevPay.services;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.repositories.BusinessAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class BusinessAccountsService {
    private BusinessAccountsRepository businessAccountsRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public BusinessAccountsService(BusinessAccountsRepository businessAccountsRepository, PasswordEncoder passwordEncoder)
    {
        this.businessAccountsRepository = businessAccountsRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public BusinessAccounts save(BusinessAccounts businessAccounts)
    {
        BusinessAccounts temp = new BusinessAccounts();
        if(businessAccounts.getBusiness_id() != null)
        {
            temp = this.get(businessAccounts.getBusiness_id());
        }
        if(businessAccounts.getPassword()!= null)
        {
            businessAccounts.setPassword(passwordEncoder.encode(businessAccounts.getPassword()));
        }
        else if(temp != null)
        {
            businessAccounts.setPassword(temp.getPassword());
        }
        return businessAccountsRepository.save(businessAccounts);
    }
    public BusinessAccounts get(Integer id)
    {
        Optional<BusinessAccounts> result = businessAccountsRepository.findById(id);
        return result.orElse(null);
    }
    public BusinessAccounts getByUsername(String username)
    {
        Optional<BusinessAccounts> result = businessAccountsRepository.findByUsername(username);
        return result.orElse(null);
    }
}
