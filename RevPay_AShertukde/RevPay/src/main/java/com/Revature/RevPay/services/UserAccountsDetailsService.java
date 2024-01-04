package com.Revature.RevPay.services;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.models.UserAccountsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAccountsDetailsService implements UserDetailsService {
    @Autowired
    private UserAccountsService userAccountsService;
    @Autowired
    private BusinessAccountsService businessAccountsService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccounts user = userAccountsService.getByUsername(username);
        BusinessAccounts business = businessAccountsService.getByUsername(username);
        return new UserAccountsUserDetails(user,business);
    }
}
