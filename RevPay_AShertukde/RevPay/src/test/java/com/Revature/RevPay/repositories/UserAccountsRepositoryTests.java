package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.UserAccounts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserAccountsRepositoryTests {
    //Mockito used in service unit testing, here I wanted to use the H2 fictional database for testing
    @Autowired
    private UserAccountsRepository userAccountsRepository;
    //Testing Save Function of JPA repository
    @Test
    public void UserAccountsRepository_Save_ReturnSavedUser(){
        //arrange
        UserAccounts userAccounts = UserAccounts.builder().username("testing").
                password("testing").email("testing@gmail.com").phone("123456789").
                firstname("testing").lastname("lastname").build();
        //act
        UserAccounts savedAccount = userAccountsRepository.save(userAccounts);
        //assert
        Assertions.assertThat(savedAccount).isNotNull();
        Assertions.assertThat(savedAccount.getUserid()).isGreaterThan(0);
    }
}
