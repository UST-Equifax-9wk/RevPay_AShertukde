package com.Revature.RevPay.services;

import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.models.UserAccountsUserDetails;
import com.Revature.RevPay.repositories.UserAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountsService{
    private final UserAccountsRepository userAccountsRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserAccountsService(UserAccountsRepository userAccountsRepository, PasswordEncoder passwordEncoder)
    {
        this.userAccountsRepository = userAccountsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccounts save(UserAccounts user)
    {
        UserAccounts temp = new UserAccounts();
        if(user.getUserid() != null) {
            temp = this.get(user.getUserid());
        }
        if(user.getPassword() != null)
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        else if(temp != null){
            user.setPassword(temp.getPassword());
        }
        return userAccountsRepository.save(user);
    }

    public UserAccounts get(Integer id)
    {
        Optional<UserAccounts> result = userAccountsRepository.findById(id);
        return result.orElse(null);
    }
    public UserAccounts getByUsername(String username)
    {
        Optional<UserAccounts> result = userAccountsRepository.findByUsername(username);
        return result.orElse(null);
    }
    public UserAccounts getByPhoneNumber(String phone)
    {
        Optional<UserAccounts> result = userAccountsRepository.findByPhone(phone);
        return result.orElse(null);
    }
    public UserAccounts getByEmail(String email)
    {
        Optional<UserAccounts> result = userAccountsRepository.findByEmail(email);
        return result.orElse(null);
    }



}
