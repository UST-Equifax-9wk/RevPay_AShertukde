package com.Revature.RevPay.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserAccountsUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserAccountsUserDetails(UserAccounts userAccounts,BusinessAccounts businessAccounts) {
        if(userAccounts != null) {
            username = userAccounts.getUsername();
            password = userAccounts.getPassword();
            authorities = Stream.of(new String[]{"USER"}).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        else if(businessAccounts != null){
            username = businessAccounts.getUsername();
            password = businessAccounts.getPassword();
            authorities= Stream.of("BUSINESS").map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
