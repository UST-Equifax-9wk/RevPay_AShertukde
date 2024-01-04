package com.Revature.RevPay.services;

import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.repositories.UserAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountsService {
    private final UserAccountsRepository userAccountsRepository;
    @Autowired
    public UserAccountsService(UserAccountsRepository userAccountsRepository)
    {
        this.userAccountsRepository = userAccountsRepository;
    }

    public UserAccounts save(UserAccounts user)
    {
        return userAccountsRepository.save(user);
    }

    public UserAccounts get(Integer id)
    {
        Optional<UserAccounts> result = userAccountsRepository.findById(id);
        return result.get();
    }
    public UserAccounts getByUsername(String username)
    {
        Optional<UserAccounts> result = userAccountsRepository.findByUsername(username);
        return result.get();
    }
}
