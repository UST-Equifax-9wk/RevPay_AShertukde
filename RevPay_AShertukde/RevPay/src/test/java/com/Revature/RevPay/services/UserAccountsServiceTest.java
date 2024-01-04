package com.Revature.RevPay.services;

import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.repositories.UserAccountsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountsServiceTest {
    @Mock
    private UserAccountsRepository userAccountsRepository;
    @InjectMocks
    private UserAccountsService userAccountsService;
    @Test
    public void UserAccountsService_GetUserAccountsByUsername_ReturnsUserAccount (){
        UserAccounts userAccounts = UserAccounts.builder().username("testing").
                password("testing").email("testing@gmail.com").phone("123456789").
                firstname("testing").lastname("lastname").build();
        when(userAccountsRepository.findByUsername(Mockito.any(String.class))).thenReturn(Optional.ofNullable(userAccounts));
        assert userAccounts != null;
        UserAccounts savedAccount = userAccountsService.getByUsername(userAccounts.getUsername());
        Assertions.assertThat(savedAccount).isEqualTo(userAccounts);
    }
    @Test
    public void UserAccountsService_GetUserAccountsByEmail_ReturnsUserAccount (){
        UserAccounts userAccounts = UserAccounts.builder().username("testing").
                password("testing").email("testing@gmail.com").phone("123456789").
                firstname("testing").lastname("lastname").build();
        when(userAccountsRepository.findByEmail(Mockito.any(String.class))).thenReturn(Optional.ofNullable(userAccounts));
        assert userAccounts != null;
        UserAccounts savedAccount = userAccountsService.getByEmail(userAccounts.getEmail());
        Assertions.assertThat(savedAccount).isEqualTo(userAccounts);
    }
    @Test
    public void UserAccountsService_GetUserAccountsByPhone_ReturnsUserAccount (){
        UserAccounts userAccounts = UserAccounts.builder().username("testing").
                password("testing").email("testing@gmail.com").phone("123456789").
                firstname("testing").lastname("lastname").build();
        when(userAccountsRepository.findByPhone(Mockito.any(String.class))).thenReturn(Optional.ofNullable(userAccounts));
        assert userAccounts != null;
        UserAccounts savedAccount = userAccountsService.getByPhoneNumber(userAccounts.getPhone());
        Assertions.assertThat(savedAccount).isEqualTo(userAccounts);
    }
    @Test
    public void UserAccountsService_GetUserAccountsById_ReturnsUserAccount (){
        UserAccounts userAccounts = UserAccounts.builder().userid(1).username("testing").
                password("testing").email("testing@gmail.com").phone("123456789").
                firstname("testing").lastname("lastname").build();
        when(userAccountsRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(userAccounts));
        assert userAccounts != null;
        UserAccounts savedAccount = userAccountsService.get(userAccounts.getUserid());
        Assertions.assertThat(savedAccount).isEqualTo(userAccounts);
    }
}
